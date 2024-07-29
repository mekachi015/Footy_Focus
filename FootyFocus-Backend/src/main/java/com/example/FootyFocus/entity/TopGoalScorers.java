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

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    private String nationality;
//
//    private String section;
//
//    //from team
//    private Long teamId;
//    private String crest;
//    private String venue;
//
//    //actual information
//    private int matchesPlayed;
//    private int goals;
//    private  int assists;
//    private int penalties;
//
//
//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //From competition
    private String competitionName;
    private String competitionEmblem;

    //from season
    private String seasonStartDate;
    private String seasonEndDate;
    private String currentMatchDay;

    //from the list of scorers
    //from players
    private String playerName;
    private String nationality;
    private String section;// player position

    //from team
    private String teamName;
    private String teamCrest;
    private String venue;

    //from the rest of the json
    private int matchesPlayed;
    private int noGoals; //the number of goals scored
    private int noAssist; // the number of assists scored
    private int penalities;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionEmblem() {
        return competitionEmblem;
    }

    public void setCompetitionEmblem(String competitionEmblem) {
        this.competitionEmblem = competitionEmblem;
    }

    public String getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(String seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public String getSeasonEndDate() {
        return seasonEndDate;
    }

    public void setSeasonEndDate(String seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

    public String getCurrentMatchDay() {
        return currentMatchDay;
    }

    public void setCurrentMatchDay(String currentMatchDay) {
        this.currentMatchDay = currentMatchDay;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCrest() {
        return teamCrest;
    }

    public void setTeamCrest(String teamCrest) {
        this.teamCrest = teamCrest;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getNoGoals() {
        return noGoals;
    }

    public void setNoGoals(int noGoals) {
        this.noGoals = noGoals;
    }

    public int getNoAssists() {
        return noAssist;
    }

    public void setNoAssists(int noAssists) {
        this.noAssist = noAssists;
    }

    public int getPenalties() {
        return penalities;
    }

    public void setPenalties(int penalties) {
        this.penalities = penalties;
    }
}





