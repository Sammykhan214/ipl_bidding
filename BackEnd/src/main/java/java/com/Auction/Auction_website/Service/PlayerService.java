package com.Auction.Auction_website.Service;

import com.Auction.Auction_website.DTO.PlayerFullDTO;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;

import java.util.List;

public interface PlayerService {
    List<PlayerFullDTO> getAllPlayers();
    List<PlayerFullDTO>getPlayerByStatus(AuctionStatus status);
    PlayerFullDTO getPlayerById(Long playerId);
};
