package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.LeagueStandings;
import com.example.FootyFocus.service.LeagueStandingsServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v4/competitions/")
public class LeagueStandingsController {

    @Autowired
    private LeagueStandingsServiceAPI leagueStandingsServiceAPI;

    @GetMapping("/{leagueCode}/standings")
    public List<LeagueStandings> getStandings(
            @PathVariable String leagueCode, @RequestParam int season) {
        return leagueStandingsServiceAPI.fetchStandingsFromApi(leagueCode, season);
    }
}
