package com.example.FootyFocus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Transactional
@Setter
@Getter
@Entity
public class detailedPlayer { // a method for the detailed player information page

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;

    private String dateOfBirth;

    private String nationality;

    private String section;

    private int shirtNumber;

    //from current team
    private String teamName;
    private String teamCrest;
    private String teamVenue;






}
