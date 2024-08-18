package com.example.FootyFocus.controller;

import com.example.FootyFocus.service.APIServices.PlayerSearchApiService;
import org.springframework.stereotype.Controller;
import com.example.FootyFocus.entity.PlayerSearch;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerSearchController {
    private final PlayerSearchApiService playerSearchApiService;

    public PlayerSearchController(PlayerSearchApiService playerSearchApiService) {
        this.playerSearchApiService = playerSearchApiService;
    }

    @GetMapping
    public List<PlayerSearch> searchPlayers(@RequestParam String search, @RequestParam int league, @RequestParam int season) {
        return playerSearchApiService.fetchPlayersFromApi(search, league, season);
    }
}
