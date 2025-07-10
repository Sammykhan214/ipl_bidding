package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class PlayerController {
    @Autowired
    PlayerService player_service;
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player>players=player_service.getPlayerInformation();
        return ResponseEntity.ok(players);
    }

}
