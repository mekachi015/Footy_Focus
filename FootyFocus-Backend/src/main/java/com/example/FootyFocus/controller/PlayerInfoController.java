package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.PlayerInfo;
import com.example.FootyFocus.service.APIServices.SearchPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerInfoController {



    @Autowired
    private SearchPlayerService searchPlayerService;

    @GetMapping
    public List<PlayerInfo> searchPlayers(
            @RequestParam String search,
            @RequestParam int league,
            @RequestParam int season) {
        return searchPlayerService.fetchPlayerInfoFromApi(search, league, season);
    }
}
