package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.DTO.PlayerFullDTO;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.Auction.Auction_website.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auction/players")
public class PlayerController {
    @Autowired
    PlayerService player_service;

    @GetMapping
    public ResponseEntity<List<PlayerFullDTO>> getPlayersByStatus(@RequestParam(required = false)
                                                           String status){
        System.out.println("📩 API Hit: /auction/players with status=" + status);
        if(status==null){
            return ResponseEntity.ok(player_service.getAllPlayers());
        }
        else{
            try{
            AuctionStatus auction_status =AuctionStatus.valueOf(status.toUpperCase());
            List<PlayerFullDTO>players=player_service.getPlayerByStatus(auction_status);
            return ResponseEntity.ok(players);
        }
        catch (Exception e){
return ResponseEntity.badRequest().body(Collections.emptyList());
            }
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerFullDTO> getPlayerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(player_service.getPlayerById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
