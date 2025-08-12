package com.Auction.Auction_website.Mediator;

import com.Auction.Auction_website.Requests.BidRequest;

public interface AuctionMediator {
    void handlePlaceBid(BidRequest bidRequest);
    void handleFinalizeBid(Long playerId);
}
