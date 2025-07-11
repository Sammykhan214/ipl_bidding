package com.Auction.Auction_website.Service;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayerInformation();
    List<Player>getPlayerByStatus(AuctionStatus status);
};
