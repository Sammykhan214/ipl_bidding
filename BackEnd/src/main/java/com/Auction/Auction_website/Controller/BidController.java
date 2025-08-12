package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.DTO.BidDTO;
import com.Auction.Auction_website.Mediator.AuctionMediator;
import com.Auction.Auction_website.Requests.BidRequest;
import com.Auction.Auction_website.Service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/auction/bid")
public class BidController {
    private final AuctionMediator auctionMediator;

    public BidController(AuctionMediator auctionMediator) {
        this.auctionMediator = auctionMediator;
    }

    @PostMapping
    public ResponseEntity<String> placeBid(@RequestBody BidRequest req) {
        auctionMediator.handlePlaceBid(req);
        return ResponseEntity.ok("Bid Placed Successfully");
    }
}

