package com.example.FootyFocus.dto;

import com.example.FootyFocus.entity.TopScorer;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiResponse {

    @JsonProperty("scorers")
    private List<TopScorer> topScorers;

    public List<TopScorer> getTopScorers(){
        return topScorers;
    }

    public void setTopScorers(List<TopScorer> topScorers){
        this.topScorers = topScorers;
    }

    public List<TopScorer> getScorers() {
        return topScorers;
    }
}
