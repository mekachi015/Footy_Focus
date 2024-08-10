package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.TopGoalScorer;
import com.example.FootyFocus.service.APIServices.TopScorerApiSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v4/competitions/")
@CrossOrigin(origins = "http://localhost:4200")
public class TopGoalScorersController {


    @Autowired
    private TopScorerApiSerive topScorerApiSerive;

    @GetMapping("/{leagueCode}/scorers")
    public List<TopGoalScorer>getTopGoalScorers(@PathVariable String leagueCode,
                                                @RequestParam int season){
        return topScorerApiSerive.fetchTopScorersFromApi(leagueCode, season);
    }


}
