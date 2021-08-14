package io.github.deepshiv126.design.auction.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuctionEntity implements Serializable {

    private String id;
    private String type;
    private String name;
    private String description;
    private Long timeOfAuction;
    private String itemName;
    private String item;
    private Long startingBid;
    private Long maxBid;
    private Long bidIncrement;

    public AuctionEntity() {
    }

    public AuctionEntity(String id, String type, String name, String description, Long timeOfAuction, String itemName, String item, Long startingBid, Long maxBid, Long bidIncrement) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.timeOfAuction = timeOfAuction;
        this.itemName = itemName;
        this.item = item;
        this.startingBid = startingBid;
        this.maxBid = maxBid;
        this.bidIncrement = bidIncrement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTimeOfAuction() {
        return timeOfAuction;
    }

    public void setTimeOfAuction(Long timeOfAuction) {
        this.timeOfAuction = timeOfAuction;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(Long startingBid) {
        this.startingBid = startingBid;
    }

    public Long getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(Long maxBid) {
        this.maxBid = maxBid;
    }

    public Long getBidIncrement() {
        return bidIncrement;
    }

    public void setBidIncrement(Long bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    @Override
    public String toString() {
        return "AuctionEntity{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", startingBid=" + startingBid +
                ", maxBid=" + maxBid +
                ", bidIncrement=" + bidIncrement +
                '}';
    }
}
