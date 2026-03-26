package com.example.FootyFocus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //from resultSet
    private int totalMatches;
    private String firstDay; //first day of matchday
    private String lastDay; //last day  of matchday
    private int matchesPlayed;

    //from competition
    private String compName;
    private String compEmblem;
    private String compType;

    //from season
    private String startDate;
    private String endDate;
    private String currentMatchdate;

    private String utcDate; // start date and time for match

    //from home team
    private String homeTeamName;
    private String homeTeamCrest;
    private String homeTeamShortName;

    //from away team
    private String awayTeamName;
    private String awayTeamCrest;
    private String awayTeamShortName;

    //from score
    private int homeFullScore;
    private int awayFullScore;

    private int homeHalfScore;
    private int awayHalfScore;

    //from referee
    private String refName;
    private String refNationality;
}
