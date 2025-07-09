package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.DTO.BidRequest;
import com.Auction.Auction_website.Service.BidService;
import com.Auction.Auction_website.Service.Impl.BidServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auction")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }
    @PostMapping("/bid")
    public ResponseEntity<String>placeBid(@RequestBody BidRequest req){
        bidService.placeBid(req);
        return ResponseEntity.ok("Bid Placed Successfully");
    }
}
