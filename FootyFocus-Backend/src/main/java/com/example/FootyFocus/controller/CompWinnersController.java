package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.CompWinners;
import com.example.FootyFocus.service.CompWinnersApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v4/competitions/")
public class CompWinnersController {

    @Autowired
    private CompWinnersApiService compWinnersApiService;

    @GetMapping("/{leagueCode}")
    public List<CompWinners> getLast15SeasonWinners(@PathVariable String leagueCode) {
        return compWinnersApiService.fetchingWinnersFromApi(leagueCode);

    }
}
