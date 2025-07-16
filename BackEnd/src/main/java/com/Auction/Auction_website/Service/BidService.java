package com.Auction.Auction_website.Service;

import com.Auction.Auction_website.DTO.BidDTO;
import com.Auction.Auction_website.Requests.BidRequest;

import java.util.List;

public interface BidService {
    void  placeBid(BidRequest req);
    List<BidDTO> getBidsForPlayer(Long playerId);
}
