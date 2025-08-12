package com.Auction.Auction_website.Mediator;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Requests.BidRequest;
import com.Auction.Auction_website.Service.BidService;
import com.Auction.Auction_website.Service.AuctionService;
import com.Auction.Auction_website.Controller.BidWebSocketController;
import org.springframework.stereotype.Component;

@Component
public class AuctionMediatorImpl implements AuctionMediator {

    private final BidService bidService;
    private final AuctionService auctionService;
    private final BidWebSocketController bidWebSocketController;

    public AuctionMediatorImpl(
            BidService bidService,
            AuctionService auctionService,
            BidWebSocketController bidWebSocketController) {
        this.bidService = bidService;
        this.auctionService = auctionService;
        this.bidWebSocketController = bidWebSocketController;
    }

    @Override
    public void handlePlaceBid(BidRequest bidRequest) {
        // 1️⃣ Save bid in system
        bidService.placeBid(bidRequest);

        // 2️⃣ Notify via WebSocket
        bidWebSocketController.broadcastBidUpdate(bidRequest);
    }

    @Override
    public void handleFinalizeBid(Long playerId) {
        // 1️⃣ Finalize logic from AuctionService
        auctionService.finaliseBid(playerId);
        Player nextPlayer = auctionService.moveToNextPlayer();
        if (nextPlayer != null) {
            System.out.println("➡ Next player on auction: " + nextPlayer.getName());
            bidWebSocketController.broadcastNextPlayer(nextPlayer);
        } else {
            System.out.println("🏁 Auction ended. No next player found.");
            auctionService.endAuction();
            bidWebSocketController.broadcastAuctionEnd();
        }
        // 2️⃣ Optional: Broadcast that the player is sold
        // Could create a DTO for sold player info
    }
}
