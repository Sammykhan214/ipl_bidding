package com.Auction.Auction_website.Service.Impl;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Repository.Player_Repo;
import com.Auction.Auction_website.Service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final Player_Repo player_repo;
            public PlayerServiceImpl(Player_Repo player_repo){
                this.player_repo=player_repo;}
    @Override
    public List<Player> getPlayerInformation() {
        List<Player>player=player_repo.findAll();
        Collections.sort(player, Comparator.comparing(Player::getId));
        return player;
    }
}
