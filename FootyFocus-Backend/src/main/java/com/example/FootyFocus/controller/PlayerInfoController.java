package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.PlayerInfo;
import com.example.FootyFocus.service.APIServices.SearchPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
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
