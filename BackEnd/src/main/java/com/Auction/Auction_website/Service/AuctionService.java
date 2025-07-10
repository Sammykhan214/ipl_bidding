package com.Auction.Auction_website.Service;

import com.Auction.Auction_website.Entity.Player;

public interface AuctionService {
    Player getCurrentPlayer();
    void startAuction(Long playerId);
    void moveToNextPlayer();
}
