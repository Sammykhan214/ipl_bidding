package com.Auction.Auction_website.Util;

import org.springframework.stereotype.Component;

@Component
public class AuctionState {
    private Long currentPlayerId;

    public Long getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(Long currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }
}
