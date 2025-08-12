package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.DTO.BidDTO;
import com.Auction.Auction_website.Requests.BidRequest;
import com.Auction.Auction_website.Service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction/bid")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }
    @PostMapping
    public ResponseEntity<String>placeBid(@RequestBody BidRequest req){
        bidService.placeBid(req);
        return ResponseEntity.ok("Bid Placed Successfully");
    }
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<BidDTO>> getPlayerBids(@PathVariable Long playerId) {
        return ResponseEntity.ok(bidService.getBidsForPlayer(playerId));
    }
}
