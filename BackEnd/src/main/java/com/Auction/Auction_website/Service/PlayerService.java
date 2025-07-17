package com.Auction.Auction_website.Service;

import com.Auction.Auction_website.DTO.PlayerFullDTO;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.Auction.Auction_website.Requests.playerUpdateRequest;

import java.util.List;

public interface PlayerService {
    List<PlayerFullDTO> getAllPlayers();
    List<PlayerFullDTO>getPlayerByStatus(AuctionStatus status);
    PlayerFullDTO getPlayerById(Long playerId);
    public Player addPlayer(playerUpdateRequest player);
    public void deletePlayer(Long playerId);
    public Player updatelayer(Long playerId, playerUpdateRequest req);
};
