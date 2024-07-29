package com.example.FootyFocus.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Transactional
@Setter
@Getter
@Entity

@Table(name = "topGoalScorer")
public class TopGoalScorers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nationality;

    private String section;

    //from team
    private Long teamId;
    private String crest;
    private String venue;

    //actual information
    private int matchesPlayed;
    private int goals;
    private  int assists;
    private int penalties;

}
