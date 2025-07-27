package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.Csv.PlayerImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auction/admin/import")
public class ImportController {
    @Autowired
    private PlayerImportService playerImportService;
    @PostMapping("players")
    public String importplayers() throws IOException{
        playerImportService.ImportPlayercsv();
        return "Player imported successfully";
    }
    @PostMapping("/stats")
    public String importStats() throws IOException{
        playerImportService.ImportPlayerStatsCsv();
        return "stats imported successfully";
    }


}
