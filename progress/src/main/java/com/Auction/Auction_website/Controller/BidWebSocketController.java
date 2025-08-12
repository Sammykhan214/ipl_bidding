package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Requests.BidRequest;
import com.Auction.Auction_website.Service.Impl.BidServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class BidWebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private BidServiceImpl bidService;
    @MessageMapping("app/bid")
    public void broadcastBidUpdate(BidRequest message) {
        bidService.placeBid(message);
        messagingTemplate.convertAndSend("/topic/bid", message);
    }
}
