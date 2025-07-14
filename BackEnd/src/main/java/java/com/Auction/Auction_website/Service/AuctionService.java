package com.Auction.Auction_website.Service;

import com.Auction.Auction_website.DTO.PlayerMiniDTO;
import com.Auction.Auction_website.Entity.Player;

import java.util.List;

public interface AuctionService {
    PlayerMiniDTO getCurrentPlayer();
    void startAuction(Long playerId);
    void moveToNextPlayer();
    void resetAuction();
    void endAuction();
    Boolean isAuctionRunning();
    void finaliseBid(Long PlayerId);
}
