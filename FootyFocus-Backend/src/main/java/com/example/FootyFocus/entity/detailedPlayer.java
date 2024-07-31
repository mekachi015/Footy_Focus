package com.example.FootyFocus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;


@Transactional
@Setter
@Getter
@Entity
public class detailedPlayer { // a method for the detailed player information page

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //players full name

    private String dateOfBirth;

    private String section; // the position in which the player plays

    private String nationality;

    private int shirtNumber;

    //from current team

    //current team: areas
    private String areaName; // where the
    private String countryflag;  // the flag is for the country that the league is in

    //from current team
    private String teamName;
    private String teamCrest; //the logo for the team
    private String teamVenue; // the teams home stadium

    //from contract
    private String contractStartDate;
    private String contractEndDate;

}
