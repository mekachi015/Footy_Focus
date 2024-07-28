package com.example.FootyFocus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class TopScorer {

    @Id
    private Long id;
    private String playerName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String section;
    private Integer playedMatches;
    private Integer goals;
    private Integer assists;
    private Integer penalties;

    // Additional fields for the team
    private Long teamId;
    private String teamName;
    private String shortName;
    private String tla;
    private String crest;

    // Getters and setters
    // Constructor(s)
}
