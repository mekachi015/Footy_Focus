package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.Matches;
import com.example.FootyFocus.service.APIServices.MatchesApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v4/competitions/")
@CrossOrigin(origins = "http://localhost:4200")
public class MatchesController {

    @Autowired
    private MatchesApiService matchesApiService;

    @GetMapping("/{competitionCode}/matches")
    public List<Matches> getMatches(@PathVariable String competitionCode, @RequestParam int matchday, @RequestParam int season){
        return  matchesApiService.fetchMatchesFromApi(competitionCode,matchday,season);
    }
}
