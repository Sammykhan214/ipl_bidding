package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Mediator.AuctionMediator;
import com.Auction.Auction_website.Requests.AuctionRequest;
import com.Auction.Auction_website.DTO.PlayerMiniDTO;
import com.Auction.Auction_website.Service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionService auctionService;
    private final AuctionMediator auctionMediator;

    @Autowired
    public AuctionController(AuctionService auctionService,AuctionMediator mediator) {
        this.auctionService = auctionService;
        this.auctionMediator=mediator;
    }
    @GetMapping("/current-player")
    public ResponseEntity<PlayerMiniDTO> getCurrentPlayer(){
        PlayerMiniDTO player = auctionService.getCurrentPlayer();
        return ResponseEntity.ok(player);
    }
    @PostMapping("/startAuction")
    public ResponseEntity<String> startAuction(@RequestBody AuctionRequest req){
        auctionService.startAuction(req.getStartPlayerId());
        return ResponseEntity.ok("Auction started for player ID: " + req.getStartPlayerId());
    }
    @PostMapping("/next-player")
    public ResponseEntity<String> moveToNextPlayer() {
      Player player= auctionService.moveToNextPlayer();
        return ResponseEntity.ok("Moved to next player."+player);
    }
    @PostMapping("/reset")
    public ResponseEntity<String> resetAuction() {
        auctionService.resetAuction();
        return ResponseEntity.ok("Auction reset successfully. You can start the Auction.");
    }
    @PostMapping("/end")
    public ResponseEntity<String> endAuction() {
        auctionService.endAuction();
        return ResponseEntity.ok("Auction ended manually.");
    }

    @GetMapping("/running_status")
    public ResponseEntity<Map<String,Boolean>> getRunningStatus(){
        boolean status=auctionService.isAuctionRunning();
        Map<String,Boolean>m=new HashMap<>();
        m.put("running",status);
        return ResponseEntity.ok(m);
    }
    @PostMapping("/finalize/{playerId}")
    public ResponseEntity<String> finalizeBid(@PathVariable Long playerId) {
        auctionMediator.handleFinalizeBid(playerId);
        return ResponseEntity.ok("✅ Bid finalized");
    }



}
