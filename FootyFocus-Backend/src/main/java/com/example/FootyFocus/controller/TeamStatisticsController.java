package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.TeamStatistics;
import com.example.FootyFocus.service.APIServices.SearchTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
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
