package com.example.FootyFocus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlayerSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //from player
    private String firstName;
    private String lastName;
    private int playerAge;

    //from birth
    private String dateOfBirth;
    private String placeOfBirth;
    private String countryOfBirth;

    private int height;
    private int weight;
    private boolean injuredStatus;
    private String playerPhoto;


    //from statistics
    //from team
    private String teamName;
    private String teamLogo;

    //from league
    private String leagueName;
    private String leagueLogo;


    //from games
    private int noAppearences;
    private int minutesPlayed;
    private String position;

    private float rating;
    private boolean captain;

    //from shots
    private int shotsTaken;
    private int shotsOnTarget;

    //from goals
    private int totalGoalsScored;
    private int goalsConceeded;
    private int noAssits;
    private String noSaves;

    //from passes
    private int totalPasses;
    private int totalKeyPasses;
    private int passingAccuracy;

    //from tackles
    private int totalTackles;
    private int blocks;
    private int interceptions;

    //from duels
    private int totalDuels;
    private int duelsWon;

    //from dribbles
    private int dribbleAttempts;
    private int successfullDribbles;

    //from fouls
    private int foulsDrawn;
    private int foulsCommitted;

    //from cards
    private int noYellowCards;
    private int noYellowRedCards;
    private int noRedCards;

    //from penalty
    private int penaltiesWon;
    private int penaltiesCommited;
    private int penaltiesScored;
    private int penaltiesMissed;
    private int penalitesSaved;
}
