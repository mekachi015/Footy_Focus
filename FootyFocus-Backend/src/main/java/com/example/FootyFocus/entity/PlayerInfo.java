package com.example.FootyFocus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PlayerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;
    private String lastname;
    private int age;
    private String height;
    private String weight;
    private boolean injured;
    private String photo;

    private String birthDate;
    private String birthPlace;

    private String teamName;
    private String teamLogo;

    private int appearances;
    private String position;
    //private int rating;
    private boolean captain;

    private int totalShots;
    private int shotsOnTarget;

    private int goals;
    private int goalsConceded;
    private int assists;
    private int saves;  // Nullable field

    private int totalPasses;
    private int keyPasses;
    private int passAccuracy;

    private int totalTackles;
    private int blocks;
    private int interceptions;

    private int totalDuels;
    private int duelsWon;

    private int dribbleAttempts;
    private int successfulDribbles;

    private int foulsDrawn;
    private int foulsCommitted;

    private int yellowCards;
    private int yellowRedCards;
    private int redCards;

    private int penaltiesWon;
    private int penaltiesCommitted;
    private int penaltiesScored;
    private int penaltiesMissed;
    private int penaltiesSaved;

}
