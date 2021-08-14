package io.github.deepshiv126.design.auction.system.service.impl;

import io.github.deepshiv126.design.auction.system.entity.AuctionEntity;
import io.github.deepshiv126.design.auction.system.service.AuctionProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class has one public method which accepts input json array list and process the data.
 *
 * @author deepakshivanandappa
 */
public class AuctionProcessingServiceImpl implements AuctionProcessingService {

    private static final Logger LOG = LoggerFactory.getLogger(AuctionProcessingServiceImpl.class);

    // intent was to keep it as instance variable, but changed it.
    private static final Map<String, Queue<AuctionEntity>> AUCTION_BIDDERS_QUEUE = new WeakHashMap<>();
    private static final Map<String, Queue<AuctionEntity>> AUCTION_WINNERS_QUEUE = new WeakHashMap<>();
    private static final Map<String, Long> RUNNING_MAX_BID_AMOUNT_COUNTER_PER_AUCTION_ITEMS = new WeakHashMap<>();
    private static final Map<String, Long> TIME_KEEPER = new WeakHashMap<>();

    public AuctionProcessingServiceImpl() {
    }

    /**
     * This method takes a list of json objects and rate of data ingestion,
     * process based on defined auction system rules.
     *
     * @param auctionEntityList   List of Json objects.
     * @param rateOfDataIngestion Rate of Data ingestion.
     */
    public void processAuctionEntities(final List<AuctionEntity> auctionEntityList, final Long rateOfDataIngestion) {

        if (null == auctionEntityList || auctionEntityList.isEmpty())
            LOG.error("Invalid or Empty Input Input");

        this.schedulerToCleanUpExpiredAuctionItem();

        auctionEntityList.forEach(auctionEntity -> {
            this.controlDataIngestion(rateOfDataIngestion);
            if (auctionEntity.getType().toUpperCase().equals(AuctionEntityType.NEWITEM.toString().toUpperCase())) {
                this.addNewAuctionItem(auctionEntity);
            }
            if (auctionEntity.getType().toUpperCase().equals(AuctionEntityType.BID.toString().toUpperCase())) {
                this.addBidderToAuctionItem(auctionEntity);
            }
        });
    }

    /**
     * This method adds New Auction Item.
     *
     * @param newAuctionItem Auction Item entity.
     */
    private void addNewAuctionItem(final AuctionEntity newAuctionItem) {
        Queue<AuctionEntity> individualItemBiddersQueue = new PriorityQueue<>((firstBidder, nextBidder) -> {
            // instructing to sort newly added bidders, based on their initial/incremented Bid Amount in ascending order.
            // ascending order helps to find losing bidder and increment his bid amount until reaches it his max value.
            if (firstBidder.getStartingBid() < nextBidder.getStartingBid()) return -1;
            if (firstBidder.getStartingBid() > nextBidder.getStartingBid()) return 1;
            // if two bidders while comparing having same bid amount,
            // will retain their order as it which helps who reached max first.
            return 0;
        });
        Queue<AuctionEntity> individualItemWinnersQueue = new PriorityQueue<>((firstBidder, nextBidder) -> {
            // instructing to sort bidders, based on their current bid amount in descending order.
            if (firstBidder.getStartingBid() > nextBidder.getStartingBid()) return -1;
            if (firstBidder.getStartingBid() < nextBidder.getStartingBid()) return 1;
            return 0;
        });
        AUCTION_BIDDERS_QUEUE.put(newAuctionItem.getId(), individualItemBiddersQueue);
        AUCTION_WINNERS_QUEUE.put(newAuctionItem.getId(), individualItemWinnersQueue);
        RUNNING_MAX_BID_AMOUNT_COUNTER_PER_AUCTION_ITEMS.put(newAuctionItem.getId(), Long.valueOf(Integer.MIN_VALUE));
        TIME_KEEPER.put(newAuctionItem.getId(), System.currentTimeMillis() + newAuctionItem.getTimeOfAuction() * 1000);
        LOG.debug("NEW AUCTION ITEM is added to the system. {}", buildLogMessage(newAuctionItem));
    }

    /**
     * This method adds/updates Bidder against Auction Item.
     *
     * @param bidderEntity Bidder entity.
     */
    private void addBidderToAuctionItem(final AuctionEntity bidderEntity) {
        Long runningMaxBidAmountCounter = Math.max(
                RUNNING_MAX_BID_AMOUNT_COUNTER_PER_AUCTION_ITEMS.get(bidderEntity.getItem()),
                bidderEntity.getStartingBid());
        Queue<AuctionEntity> individualItemBiddersQueue = AUCTION_BIDDERS_QUEUE.get(bidderEntity.getItem());
        Queue<AuctionEntity> individualItemWinnersQueue = AUCTION_WINNERS_QUEUE.get(bidderEntity.getItem());

        if (individualItemBiddersQueue.size() < 1) {
            // until 2 bidder in the system, there is no bidding war.
            individualItemBiddersQueue.add(bidderEntity);
            LOG.debug("NEW BID :  \"{}\"  bid on item  \"{}\"  for  \"${}\"  and current winning bid amount is  \"${}\".",
                    bidderEntity.getName(), bidderEntity.getItemName(),
                    bidderEntity.getStartingBid(), runningMaxBidAmountCounter);
        } else {
            // add new bidder to system first.
            individualItemBiddersQueue.add(bidderEntity);
            LOG.debug("NEW BID::  \"{}\"  bid on item  \"{}\"  for  \"${}\"  and current winning bid amount is  \"${}\".",
                    bidderEntity.getName(), bidderEntity.getItemName(),
                    bidderEntity.getStartingBid(), runningMaxBidAmountCounter);

            runningMaxBidAmountCounter = autoIncrementsBids(individualItemBiddersQueue, individualItemWinnersQueue, runningMaxBidAmountCounter);
        }
        RUNNING_MAX_BID_AMOUNT_COUNTER_PER_AUCTION_ITEMS.put(bidderEntity.getItem(), runningMaxBidAmountCounter);
        AUCTION_BIDDERS_QUEUE.put(bidderEntity.getItem(), individualItemBiddersQueue);
        AUCTION_WINNERS_QUEUE.put(bidderEntity.getItem(), individualItemWinnersQueue);
    }

    /**
     * This is core logic of this Auction System.
     * Idea is to maintain 2 Priority Queues ( which helps in enqueue/dequeue O(log(n)).
     * Ascending Order of Queue will identify the bidder who is losing,
     * dequeue it, update bid value by increment value, add back to queue.
     * <p>
     * Continue to this unit bid value exceeds Max or bidder comes to leading position.
     *
     * @param individualItemBiddersQueue Priority Queue holding bidders in sorted order of bidding amount.
     * @param individualItemWinnersQueue Priority Queue holding bidders hit max bid amount.
     * @param runningMaxBidAmountCounter Running Max Bid Amount.
     */
    private Long autoIncrementsBids(final Queue<AuctionEntity> individualItemBiddersQueue,
                                    final Queue<AuctionEntity> individualItemWinnersQueue,
                                    final Long runningMaxBidAmountCounter) {
        Long runningLocalMaxBidAmountCounter = runningMaxBidAmountCounter;
        while (true) {
            // help if any bidder losing the auction, by incrementing bid amount
            // and walk away if only bidder exists in the system.

            if (individualItemBiddersQueue.peek().getStartingBid() >= runningLocalMaxBidAmountCounter)
                if (individualItemBiddersQueue.size() == 1)
                    break;

            // get the bidder out of queue.
            AuctionEntity bidderAtLosingAuction = individualItemBiddersQueue.poll();
            // increment bid amount
            Long newBidAmount = bidderAtLosingAuction.getStartingBid() + bidderAtLosingAuction.getBidIncrement();
            // check if bid amount exceeds max amount.
            bidderAtLosingAuction.setStartingBid(
                    newBidAmount <= bidderAtLosingAuction.getMaxBid() ?
                            newBidAmount : bidderAtLosingAuction.getMaxBid());
            //add back to queue, it will get sorted based on new bid amount.
            individualItemBiddersQueue.add(bidderAtLosingAuction);
            //update running max value.
            runningLocalMaxBidAmountCounter = Math.max(runningLocalMaxBidAmountCounter, bidderAtLosingAuction.getStartingBid());
            LOG.debug(">>>>>{}", individualItemBiddersQueue.peek().toString());
            moveBidderReachedMaxToAnotherQueue(individualItemBiddersQueue, individualItemWinnersQueue);


            LOG.debug("UPDATED BID:: \"{}\"  updated bid on item  \"{}\"  for new bid amount  \"${}\"  and current winning bid amount is  \"${}\".",
                    bidderAtLosingAuction.getName(), bidderAtLosingAuction.getItemName(),
                    bidderAtLosingAuction.getStartingBid(), runningLocalMaxBidAmountCounter);
        }
        return runningLocalMaxBidAmountCounter;
    }

    /**
     * This method helps move bidder who has less auction bid value and hit max very sooner than others.
     * Moving to another queues gives opportunity for next least bidder to compete against leading bidder.
     *
     * @param individualItemBiddersQueue Priority Queue holding bidders in sorted order of bidding amount.
     * @param individualItemWinnersQueue Priority Queue holding bidders hit max bid amount.
     */
    private void moveBidderReachedMaxToAnotherQueue(final Queue<AuctionEntity> individualItemBiddersQueue,
                                                    final Queue<AuctionEntity> individualItemWinnersQueue) {
        // move bidders who already reached max bid - this way allow my logic to see next bidder at loss and help him.
        while (true) {
            // leave this last winning bidder in this bidding queue until end of time.
            if (individualItemBiddersQueue.size() == 1)
                break;
            // if top of queue is not reach max bid, then break.
            if (!individualItemBiddersQueue.peek().getStartingBid().equals(individualItemBiddersQueue.peek().getMaxBid()))
                break;
            individualItemWinnersQueue.add(individualItemBiddersQueue.poll());
        }
    }

    /**
     * At the end Winner Queue has all the Bidders who hits Max Bid amount, in order.
     * Last leading bidder need to be added to this queue, to determine whether
     * is he the winner or any body else similar max limit hit before him.
     *
     * @param auctionBiddersQueue Priority Queue holding bidders in sorted order of bidding amount.
     * @param auctionWinnersQueue Priority Queue holding bidders hit max bid amount.
     */
    private void moveLastStandingBiddersToWinnersQueue(
            final Map<String, Queue<AuctionEntity>> auctionBiddersQueue,
            final Map<String, Queue<AuctionEntity>> auctionWinnersQueue) {

        if (null == auctionBiddersQueue)
            throw new RuntimeException("Bidder Queue is empty.");

        if (null == auctionWinnersQueue)
            throw new RuntimeException("Winner Queue is empty.");

        // finally drain
        auctionBiddersQueue.forEach((auctionItemId, biddersQueue) -> {
            Queue<AuctionEntity> winnersQueue = auctionWinnersQueue.get(auctionItemId);
            while (biddersQueue.size() != 0) {
                winnersQueue.add(biddersQueue.poll());
            }
            auctionWinnersQueue.put(auctionItemId, winnersQueue);
        });
    }

    /**
     * This method is method looks up last leading bidding winner.
     *
     * @param auctionWinnersQueue Priority Queue holding bidders hit max bid amount.
     */
    private void declareWinnersForEachItem(final Map<String, Queue<AuctionEntity>> auctionWinnersQueue) {
        if (null == auctionWinnersQueue || auctionWinnersQueue.isEmpty())
            throw new RuntimeException("Auction Item couldn't recognize Winner.");

        // declare winners
        auctionWinnersQueue.forEach(((bidderEntity, biddersWinningQueue) -> {
            AuctionEntity finalWinners = biddersWinningQueue.peek();
            LOG.debug("\"{}\"  WON the bid on item  \"{}\"  for  \"${}\"  and maximum winning bid amount was  \"${}\".",
                    finalWinners.getName(), finalWinners.getItemName(),
                    finalWinners.getStartingBid(), finalWinners.getStartingBid());
        }));
    }

    /**
     * I am not a fan of this idea, I would have come up with better one.
     * <p>
     * This method simply helps to control rate of JSON ingestion into queue.
     *
     * @param rateOfDataIngestion Rate of Data ingestion.
     */
    private void controlDataIngestion(Long rateOfDataIngestion) {
        if (null == rateOfDataIngestion || rateOfDataIngestion < 0)
            throw new InvalidParameterException("Invalid rateOfDataIngestion.");

        try {
            rateOfDataIngestion *= 1000;
            Thread.sleep(rateOfDataIngestion);
        } catch (InterruptedException e) {
            LOG.debug("Exception occurred while introducing the delay in Data ingestion - {}", e.getMessage());
        }
    }

    /**
     * Schedule auction item clean up based on given expire time.
     */
    private void schedulerToCleanUpExpiredAuctionItem() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            LOG.debug("SCHEDULER:: Current Number of Items in Auction : {} ", TIME_KEEPER.size());
            if (TIME_KEEPER.size() != 0) {
                TIME_KEEPER.entrySet().forEach(e -> {
                    if (e.getValue() - System.currentTimeMillis() <= 0) {
                        this.moveLastStandingBiddersToWinnersQueue(AUCTION_BIDDERS_QUEUE,
                                AUCTION_WINNERS_QUEUE);
                        this.declareWinnersForEachItem(AUCTION_WINNERS_QUEUE);
                        AUCTION_BIDDERS_QUEUE.remove(e.getKey());
                        AUCTION_WINNERS_QUEUE.remove(e.getKey());
                        RUNNING_MAX_BID_AMOUNT_COUNTER_PER_AUCTION_ITEMS.remove(e.getKey());
                        TIME_KEEPER.remove(e.getKey());
                        LOG.debug("SCHEDULER:: Cleaned up Auction Item - {}", e.getKey());
                    } else {
                        LOG.debug("SCHEDULER:: Item {} will expire in {} seconds", e.getKey(), (e.getValue() - System.currentTimeMillis()) / 1000);
                    }
                });
            }
        }, 10, 10, TimeUnit.SECONDS);
        LOG.debug("SCHEDULER:: Initialized the Scheduler for Clean up process.");
    }

    /**
     * This methods builds the Log Message according to type of event.
     * There was a requirement to print the log message on every new event or event change.
     *
     * @param auctionEntity
     * @return String output based on type.
     */
    private String buildLogMessage(final AuctionEntity auctionEntity) {
        if (null == auctionEntity)
            throw new RuntimeException("Invalid auctionEntity.");

        if (auctionEntity.getType().toUpperCase().equals(AuctionEntityType.NEWITEM.toString().toUpperCase())) {
            return new StringBuilder()
                    .append("id='").append(auctionEntity.getId()).append('\'')
                    .append(", type='").append(auctionEntity.getType()).append('\'')
                    .append(", name='").append(auctionEntity.getName()).append('\'')
                    .append(", description='").append(auctionEntity.getDescription()).append('\'')
                    .append(", timeOfAuction='").append(auctionEntity.getTimeOfAuction()).append('\'')
                    .toString();
        }
        return new StringBuilder()
                .append("id='").append(auctionEntity.getId()).append('\'')
                .append(", type='").append(auctionEntity.getType()).append('\'')
                .append(", name='").append(auctionEntity.getName()).append('\'')
                .append(", itemName='").append(auctionEntity.getItemName()).append('\'')
                .append(", item='").append(auctionEntity.getItem()).append('\'')
                .append(", item='").append(auctionEntity.getItem()).append('\'')
                .append(", startingBid='").append(auctionEntity.getStartingBid()).append('\'')
                .append(", maxBid='").append(auctionEntity.getMaxBid()).append('\'')
                .append(", bidIncrement='").append(auctionEntity.getBidIncrement()).append('\'')
                .toString();
    }

    private enum AuctionEntityType {
        NEWITEM, BID
    }
}
