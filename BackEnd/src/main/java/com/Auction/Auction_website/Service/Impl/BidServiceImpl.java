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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BidServiceImpl implements BidService {
    private final Bid_Repo bid_repo;
    private final Team_Repo team_repo;
    private final Player_Repo player_repo;
    public BidServiceImpl(Bid_Repo bid_repo,Team_Repo team_repo,Player_Repo player_repo){
        this.bid_repo=bid_repo;
        this.player_repo=player_repo;
     this.team_repo=team_repo;
    }

    @Override
    public void placeBid(BidRequest req) {
        Player player=player_repo.findById(req.getPlayerId()).orElseThrow(
                ()-> new RuntimeException("Player Not found")
        );
       Team team=team_repo.findById(req.getPlayerId()).orElseThrow(
                ()-> new RuntimeException("Team Not found")
        );
       if(team.getBudget()<req.getAmount()){
           throw new RuntimeException("Insufficient Balance");
       }
       player.setSoldPrice(req.getAmount());
       player.setTeam(team);
       player.setStatus(AuctionStatus.SOLD);
       team.setBudget(team.getBudget()-req.getAmount());
       Bid bid=new Bid();
       bid.setAmount(req.getAmount());
       bid.setTeam(team);
       bid.setPlayer(player);
       bid.setTime(LocalDateTime.now());

       player_repo.save(player);
       team_repo.save(team);
       bid_repo.save(bid);
    }
}
