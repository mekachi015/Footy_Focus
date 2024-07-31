package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.detailedPlayer;
import com.example.FootyFocus.service.APIServices.detailedPlayerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v4/persons")
public class detailedPlayerController {


    @Autowired
    private detailedPlayerAPIService playerAPIService;
//
//    @GetMapping("/{id}")
//    public detailedPlayer getPlayerDetails(@PathVariable Long id) {
//        return playerAPIService.fetchPlayerInfoFromAPI(id);
//    }



    @GetMapping("/{id}")
    public detailedPlayer getPlayerDetails(@PathVariable Long id) {
        return playerAPIService.fetchPlayerInfoFromAPI(id);
    }
}

//    @GetMapping("/search")
//    public detailedPlayer searchPlayerByName(@RequestParam String name) {
//       Long playerID = playerAPIService.searchPlayerIdByName(name);
//
//       return playerAPIService.fetchPlayerInfoFromAPI(playerID);
//    }
//}
