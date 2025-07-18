package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Requests.BidRequest;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class BidWebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    public void broadcastBidUpdate(BidRequest message) {
        messagingTemplate.convertAndSend("/topic/bid", message);
    }
}
