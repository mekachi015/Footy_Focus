package com.example.FootyFocus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Map;


@Entity
@Getter
@Setter
public class TeamStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int leagueId;
    private String leagueName;
    private String leagueCountry;
    private String leagueLogo;
    private String leagueFlag;
    private int season;

    private int teamId;
    private String teamName;
    private String teamLogo;

    private String form;

    private int homePlayed;
    private int awayPlayed;
    private int totalPlayed;

    private int homeWins;
    private int awayWins;
    private int totalWins;

    private int homeDraws;
    private int awayDraws;
    private int totalDraws;

    private int homeLoses;
    private int awayLoses;
    private int totalLoses;

    private int goalsForHome;
    private int goalsForAway;
    private int goalsForTotal;

    private int goalsAgainstHome;
    private int goalsAgainstAway;
    private int goalsAgainstTotal;

    private int cleanSheetHome;
    private int cleanSheetAway;
    private int cleanSheetTotal;

    private int failedToScoreHome;
    private int failedToScoreAway;
    private int failedToScoreTotal;

//    @ElementCollection
//    @CollectionTable(name = "lineups")
//    @MapKeyColumn(name = "formation")
//    @Column(name = "matches_played")
//    private Map<String, Integer> lineups;

    private int yellowCards;
    private int redCards;

    // Biggest statistics
    private int biggestWinStreak;
    private int biggestDrawStreak;
    private int biggestLoseStreak;

    private String biggestHomeWin;
    private String biggestAwayWin;

    private String biggestHomeLose;
    private String biggestAwayLose;

    private int biggestGoalsForHome;
    private int biggestGoalsForAway;

    private int biggestGoalsAgainstHome;
    private int biggestGoalsAgainstAway;

}
