package com.example.FootyFocus.service;

import com.example.FootyFocus.entity.TopScorer;
import com.example.FootyFocus.repository.TopScorerRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TopScorerServiceImpl implements  TopScorerService{

    private final TopScorerRepo topScorerRepo;

    @Autowired
    public TopScorerServiceImpl(TopScorerRepo topScorerRepo){
        this.topScorerRepo = topScorerRepo;
    }



//    @Override
//    public List<TopScorer> getTopScorersBySeason(String competitionCode, Integer season){
//        return topScorerRepo.findTop10BySeason(season);
//    }
}
