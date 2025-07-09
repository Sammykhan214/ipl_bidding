package com.Auction.Auction_website.Service.Impl;

import com.Auction.Auction_website.Util.AuctionState;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Repository.Player_Repo;
import com.Auction.Auction_website.Service.AuctionService;
import org.springframework.stereotype.Service;

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
        Player player= player_repo.findById(playerId).
                orElseThrow(()->new RuntimeException(
                        "No player found with id:"+playerId));
        state.setCurrentPlayerId(playerId);
    }
}
