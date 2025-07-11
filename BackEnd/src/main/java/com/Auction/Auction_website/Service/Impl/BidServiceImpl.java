package com.Auction.Auction_website.Service.Impl;

import com.Auction.Auction_website.DTO.BidRequest;
import com.Auction.Auction_website.Entity.Bid;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Entity.Team;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.Auction.Auction_website.Repository.Bid_Repo;
import com.Auction.Auction_website.Repository.Player_Repo;
import com.Auction.Auction_website.Repository.Team_Repo;
import com.Auction.Auction_website.Service.BidService;
import com.Auction.Auction_website.Util.AuctionState;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BidServiceImpl implements BidService {
    private final Bid_Repo bid_repo;
    private final Team_Repo team_repo;
    private final Player_Repo player_repo;
    private final AuctionState state;
    public BidServiceImpl(Bid_Repo bid_repo,AuctionState state,Team_Repo team_repo,Player_Repo player_repo){
        this.bid_repo=bid_repo;
        this.player_repo=player_repo;
     this.team_repo=team_repo;
     this.state=state;
    }

    @Override
    public void placeBid(BidRequest req) {
        Player player=player_repo.findById(req.getPlayerId()).orElseThrow(
                ()-> new RuntimeException("Player Not found")
        );
       Team team=team_repo.findById(req.getPlayerId()).orElseThrow(
                ()-> new RuntimeException("Team Not found")
        );
        if (player.getStatus() == AuctionStatus.SOLD) {
            throw new RuntimeException("Player already sold.");
        }
       if(team.getBudget()<req.getAmount()){
           throw new RuntimeException("Insufficient Balance");
       }
       //update player
       player.setSoldPrice(req.getAmount());
       player.setTeam(team);
       player.setStatus(AuctionStatus.SOLD);
       player_repo.save(player);
//update team
       team.setBudget(team.getBudget()-req.getAmount());
       team_repo.save(team);

       Bid bid=new Bid();
       bid.setAmount(req.getAmount());
       bid.setTeam(team);
       bid.setPlayer(player);
       bid.setTime(LocalDateTime.now());
       bid_repo.save(bid);

    //Auto-end logic
    boolean unsold_player=player_repo.existsByStatus(AuctionStatus.UNSOLD);
    if(!unsold_player){
        state.setCurrentPlayerId(null);
        System.out.println("✅ Auction ended automatically — no unsold players remaining.");
    }


    }
}
