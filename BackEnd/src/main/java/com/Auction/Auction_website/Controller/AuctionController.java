package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.DTO.AuctionRequest;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }
    @GetMapping("/current-player")
    public ResponseEntity<Player> getCurrentPlayer(){
        Player currentPlayer = auctionService.getCurrentPlayer();
        return ResponseEntity.ok(currentPlayer);
    }
    @PostMapping("/startAuction")
    public ResponseEntity<String> startAuction(@RequestBody AuctionRequest req){
        auctionService.startAuction(req.getStartPlayerId());
        return ResponseEntity.ok("Auction started for player ID: " + req.getStartPlayerId());
    }
    @PostMapping("/next-player")
    public ResponseEntity<String> moveToNextPlayer() {
        auctionService.moveToNextPlayer();
        return ResponseEntity.ok("Moved to next player.");
    }
}
