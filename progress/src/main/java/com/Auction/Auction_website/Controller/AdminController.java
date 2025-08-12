package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.DTO.PlayerFullDTO;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Requests.playerUpdateRequest;
import com.Auction.Auction_website.Service.Impl.PlayerServiceImpl;
import com.Auction.Auction_website.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auction/admin/player")
public class AdminController {
    @Autowired
    PlayerServiceImpl player_service;
    @PostMapping("/add")
    public ResponseEntity<?>addPlayer(@RequestBody playerUpdateRequest req){
        System.out.println("Adding");
        try{
        Player player=player_service.addPlayer(req);
        return  ResponseEntity.ok(new PlayerFullDTO(player));}
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updatePlayer(@PathVariable Long id, @RequestBody playerUpdateRequest req){
        try{Player player=player_service.updatelayer(id,req);
        return ResponseEntity.ok(new PlayerFullDTO(player));}
      catch(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deletePlayer(@PathVariable Long id){
        try{player_service.deletePlayer(id);
        return ResponseEntity.ok("Player Deleted Successfully");}
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
