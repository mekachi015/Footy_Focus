package com.example.FootyFocus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CompWinners {

    //An entity that represents the a winner of a specific competion for a specific year

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leagueCode; //stores the leagues code either pl, sa, pd ect

    private String startDate; // the begining of the league

    private String endDate; // the last day of the league

    private String winnerName; //the name of the winnig team

    private String wShortName; // the winning team shortened name

    private String winnersCrest; //stores the cres for the winning team

    private String winnerVenue; // the home stadium of the winning team

    public Long getId() {
        return id;
    }

    public String getLeagueCode() {
        return leagueCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getwShortName() {
        return wShortName;
    }

    public String getWinnersCrest() {
        return winnersCrest;
    }

    public String getWinnerVenue() {
        return winnerVenue;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setLeagueCode(String leagueCode) {
        this.leagueCode = leagueCode;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public
    void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public void setwShortName(String wShortName) {
        this.wShortName = wShortName;
    }

    public void setWinnersCrest(String winnersCrest) {
        this.winnersCrest = winnersCrest;
    }

    public void setWinnerVenue(String winnerVenue) {
        this.winnerVenue = winnerVenue;
    }

}
