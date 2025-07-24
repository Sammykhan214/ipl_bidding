//package com.Auction.Auction_website.Controller;
//import com.Auction.Auction_website.Requests.BidRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//@RestController
//@RequestMapping("/auction/ws")
//public class testBidController {
//
//    @Autowired
//    private BidWebSocketController wsController;
//
//    @PostMapping("/trigger")
//    public void triggerBid(@RequestBody BidRequest req) {
//        wsController.broadcastBidUpdate(req);
//    }
//}
//
