package com.Auction.Auction_website.Service.Impl;

import com.Auction.Auction_website.Enums.AuctionStatus;
import com.Auction.Auction_website.Util.AuctionState;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Repository.Player_Repo;
import com.Auction.Auction_website.Service.AuctionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {
private final AuctionState state;
private final Player_Repo player_repo;
public AuctionServiceImpl(AuctionState state,Player_Repo repo){
    this.state=state;
    this.player_repo=repo;
}
    @Override
    public Player getCurrentPlayer() {
     Long curr_playerId=state.getCurrentPlayerId();
        if(curr_playerId==null)
            throw new RuntimeException("No player is currently in auction");
        return player_repo.findById(curr_playerId).
                orElseThrow(()->new RuntimeException(
                "No player found with id:"+curr_playerId));
    }

    @Override
    public void startAuction(Long playerId) {
        Player player = player_repo.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        if (player.getStatus() == AuctionStatus.SOLD) {
            throw new RuntimeException("This player is already sold.");
        }

        player.setStatus(AuctionStatus.ON_AUCTION);
       player_repo.save(player);

        state.setCurrentPlayerId(playerId);
    }

    @Override
    public void moveToNextPlayer() {
            Long currentPlayerId = state.getCurrentPlayerId();

            // Get all players sorted by ID
            List<Player> allPlayers = player_repo.findAll();
        Collections.sort(allPlayers, Comparator.comparing(Player::getId));

            boolean foundCurrent = false;
            Player currentPlayer = null;

            for (Player player : allPlayers) {
                if (foundCurrent) {
                    // Found next player
                    player.setStatus(AuctionStatus.ON_AUCTION);
                    state.setCurrentPlayerId(player.getId());
                   player_repo.save(player);

                    if (currentPlayer != null) {
                        // update previous player status if not already sold
                        if (currentPlayer.getTeam() == null) {
                            currentPlayer.setStatus(AuctionStatus.UNSOLD);
                        } else {
                            currentPlayer.setStatus(AuctionStatus.SOLD);
                        }
                        player_repo.save(currentPlayer);
                    }

                    return;
                }

                if (player.getId().equals(currentPlayerId)) {
                    foundCurrent = true;
                    currentPlayer = player;
                }
            }

            state.setCurrentPlayerId(null);
            throw new RuntimeException("No next player found. Auction may be over.");

    }

    @Override
    public void resetAuction() {
        List<Player>player=player_repo.findAll();

        for(Player p:player){
            if(p.getStatus()!=AuctionStatus.UNSOLD) {
                p.setStatus(AuctionStatus.UNSOLD);
                p.setTeam(null);
            }
    }
        player_repo.saveAll(player);
        state.setCurrentPlayerId(null);


}}
