package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.TopGoalScorers;
import com.example.FootyFocus.service.TopScorersAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v4/competitions/")
public class TopGoalScorerController {

//    @Autowired
//    private TopScorersAPIService topScorersAPIService;

//    @GetMapping("/{leagueCode}/scorers")
//    public TopGoalScorers getTop (@PathVariable String leagueCode, @RequestParam int season){
//        return topScorersAPIService.fetchTopScorerFromApi(leagueCode, season);
//    }


    private TopScorersAPIService topScorersAPIService;

    @Autowired
    public void TopScorersController(TopScorersAPIService topScorersAPIService) {
        this.topScorersAPIService = topScorersAPIService;
    }

    @GetMapping("/{leagueCode}/scorers")
    public ResponseEntity<?> getTopGoalScorers(@PathVariable String leagueCode, @RequestParam int season) {
        try {
            List<TopGoalScorers> topScorers = topScorersAPIService.fetchTopScorerFromApi(leagueCode, season);
            return new ResponseEntity<>(topScorers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch top scorers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
