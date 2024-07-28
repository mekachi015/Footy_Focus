package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.TopScorer;
import com.example.FootyFocus.repository.TopScorerRepo;
import com.example.FootyFocus.service.TopScorerApiService;
import com.example.FootyFocus.service.TopScorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
public class TopScorerController {

//    private final TopScorerService topScorerService;
//
//    @Autowired
//    public TopScorerController (TopScorerService topScorerService){
//        this.topScorerService = topScorerService;
//    }
//
//    @GetMapping("/scorers")
//    public ResponseEntity<List<TopScorer>> getTopScorersBySeason(
//            @PathVariable String competition,
//            @RequestParam(required = false) Integer seasons) {
//
//        if (seasons == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        List<TopScorer> topScorers = topScorerService.getTopScorersBySeason(competition, seasons);
////        return ResponseEntity.ok(topScorers);
//    }

    @Autowired
    private TopScorerApiService topScorerApiService;

    @GetMapping("/{leagueCode}/scorers?season={season}")
    public String fetchAndSaveTopScorers(@PathVariable String leagueCode, @PathVariable String season){
        try{
            topScorerApiService.fetchAndSaveTopScorers(leagueCode, season);
            return "Top scorers fetched and saved successfully";
        } catch(Exception e){
            return "An error occured while fetching and saving top scorer:" + e.getMessage();
        }
    }
}
