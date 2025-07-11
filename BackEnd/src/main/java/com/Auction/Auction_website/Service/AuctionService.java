package com.Auction.Auction_website.Service;

import com.Auction.Auction_website.Entity.Player;

import java.util.List;

public interface AuctionService {
    Player getCurrentPlayer();
    void startAuction(Long playerId);
    void moveToNextPlayer();
    void resetAuction();
    void endAuction();
}
