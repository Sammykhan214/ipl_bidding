package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.Auction.Auction_website.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auction/players")
public class PlayerController {
    @Autowired
    PlayerService player_service;
    @GetMapping
    public ResponseEntity<List<Player>> getPlayersByStatus(@RequestParam(required = false)
                                                           String status){
        if(status==null){
            return ResponseEntity.ok(player_service.getPlayerInformation());
        }
        else{
            try{
            AuctionStatus auction_status =AuctionStatus.valueOf(status.toUpperCase());
            List<Player>players=player_service.getPlayerByStatus(auction_status);
            return ResponseEntity.ok(players);
        }
        catch (Exception e){
return ResponseEntity.badRequest().body(Collections.emptyList());
            }
        }


    }
//    @GetMapping("/players/sold")
//    public ResponseEntity<List<Player>> getSoldPlayers(){
//    List<Player>players=player_service.getPlayerByStatus(AuctionStatus.SOLD);
//    return ResponseEntity.ok(players);}
//    @GetMapping("/players/unsold")
//    public ResponseEntity<List<Player>> getUnsoldPlayers(){
//        List<Player>players=player_service.getPlayerByStatus(AuctionStatus.UNSOLD);
//        return ResponseEntity.ok(players);}
//
//    @GetMapping("/players/on-auction")
//    public ResponseEntity<List<Player>> getOnAuctionPlayers(){
//        List<Player>players=player_service.getPlayerByStatus(AuctionStatus.ON_AUCTION);
//        return ResponseEntity.ok(players);}

}
