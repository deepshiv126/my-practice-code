# Challenge Prompt

Create a real-time system that emulates an auto-bid auction system. The auction system will provide the ability to
submit new items to sell and give bidders the ability to submit bids with a starting bid and an auto increment amount up
to a maximum amount even when they aren't connected to the platform.

Upon receiving a new auction item a second based countdown timer is started during which time the buyers are allowed to
submit their bids. After every bid the current winner is determined for the item. When the countdown timer hits zero the
current winning bidder is declared the winner.

## Items

### Items entries contain:

1. The type of “newItem”
2. Item identifier, name, and description
3. The time of the auction in seconds after which time the item is sold

### Bid entries contain:

1. The type of“bid”
2. Buyer identifier and name
3. Item identifier and name
4. Starting bid:The first and lowest bid the buyer is willing to offer for the item.
5. Max Bid: This maximum amount the bidder is willing to pay for the item.
6. Auto-increment amount: A dollar amount that the computer algorithm will add to the bidder's current bid each time the
   bidder is in a losing position relative to the other bidders. The algorithm should never let the current bid exceed
   the Max bid. The algorithm should only allow increments of the exact auto-increment amount.

For each auction, your algorithm should determine: Who is the winning bidder? and What is the amount of the winning bid?

## Grading

There are a few guidelines to follow while completing this exercise:

- Has appropriate usage of design patterns, concurrency, and data structures Has extendable architecture
- Has console or log output that allows interviewers to clearly understand and follow your system’s operations as it
  runs in real-time. Whenever events occur in your system (auction item added, bid received, auction closed, etc), your
  system should output a message containing both a description of the triggering event and a full listing of current
  auction listings.
- Has comprehensive unit testing (covers all major components and flows) Has production-quality code cleanliness
- Has production-quality docs on all public functions (as if someone had to work with your code)
