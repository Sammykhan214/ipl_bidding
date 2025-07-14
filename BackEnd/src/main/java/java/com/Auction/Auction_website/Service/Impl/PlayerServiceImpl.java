package com.Auction.Auction_website.Service.Impl;

import com.Auction.Auction_website.DTO.PlayerFullDTO;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.Auction.Auction_website.Repository.Player_Repo;
import com.Auction.Auction_website.Service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final Player_Repo player_repo;
            public PlayerServiceImpl(Player_Repo player_repo){
                this.player_repo=player_repo;}


    @Override
    public List<PlayerFullDTO> getAllPlayers() {
                List<Player>players=player_repo.findAll();
        System.out.println("📌 Total players from DB: " + players.size());
        return players.stream().map(player->new PlayerFullDTO(player))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerFullDTO> getPlayerByStatus(AuctionStatus status) {

                return player_repo.findByStatus(status).stream()
                        .map(player->new PlayerFullDTO(player))
                        .collect(Collectors.toList());
    }

    @Override
    public PlayerFullDTO getPlayerById(Long playerId) {
        Player player = player_repo.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        return new PlayerFullDTO(player);
    }
}
