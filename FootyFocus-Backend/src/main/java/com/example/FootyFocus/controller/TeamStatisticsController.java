package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.TeamStatistics;
import com.example.FootyFocus.service.APIServices.SearchTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "http://localhost:4200")
public class TeamStatisticsController {

    @Autowired
    private SearchTeamService searchTeamService;

    @GetMapping("/statistics")
    public ResponseEntity<TeamStatistics> getTeamStats(
            @RequestParam("league") int leagueId,
            @RequestParam("season") int season,
            @RequestParam("team") int teamId)
    {
        TeamStatistics teamStatistics = searchTeamService.fetchTeamStatisticsFromApi(leagueId, season, teamId);
        return ResponseEntity.ok(teamStatistics);
    }

}
