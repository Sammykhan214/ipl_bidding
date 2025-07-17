package com.Auction.Auction_website.Service.Impl;

import com.Auction.Auction_website.DTO.PlayerFullDTO;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.Auction.Auction_website.Repository.Player_Repo;
import com.Auction.Auction_website.Requests.playerUpdateRequest;
import com.Auction.Auction_website.Service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    @Override
    public Player addPlayer(playerUpdateRequest req) {
        Player player = new Player();
        player.setName(req.getName());
        player.setRole(req.getRole());
        player.setNationality(req.getNationality());
        player.setBasePrice(req.getBasePrice());
        player.setStatus(AuctionStatus.UNSOLD);
        return player_repo.save(player);

    }

    @Override
    public void deletePlayer(Long playerId) {
    Player player=player_repo.findById(playerId).orElseThrow(
            ()->new RuntimeException("Player not found.")
    );
    if(player.getStatus()==AuctionStatus.SOLD)
        throw new RuntimeException("Sold Players cannot be removed from auction");
    player_repo.delete(player);
    }

    @Override
    public Player updatelayer(Long playerId, playerUpdateRequest req) {
        Player player=player_repo.findById(playerId).orElseThrow(
                ()->new RuntimeException("Player not found."));
        if(player.getStatus()==AuctionStatus.SOLD)
            throw new RuntimeException("Sold Players cannot be updated");
if(req.getBasePrice()!=null)
    player.setBasePrice(req.getBasePrice());
if(req.getName()!=null)
    player.setName(req.getName());
if(req.getNationality()!=null)
    player.setNationality(req.getNationality());
if(req.getRole()!=null)
    player.setRole(req.getRole());
return player_repo.save(player);
    }
}
