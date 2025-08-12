package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Entity.Player;
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

    // Now WebSocket just sends messages, doesn't call BidService directly
    public void broadcastBidUpdate(BidRequest message) {
        messagingTemplate.convertAndSend("/topic/bid", message);
    }
    @MessageMapping("/nextPlayer")
    public void broadcastNextPlayer(Player player) {
        messagingTemplate.convertAndSend("/topic/nextPlayer", player);
    }
    public void broadcastAuctionEnd() {
        messagingTemplate.convertAndSend("/topic/auctionEnd", "Auction has ended.");
    }

}
