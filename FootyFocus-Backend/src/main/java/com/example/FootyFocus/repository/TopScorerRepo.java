package com.example.FootyFocus.repository;

import com.example.FootyFocus.entity.TopScorer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopScorerRepo extends JpaRepository<TopScorer,Long> {

//    List<TopScorer> findTop10BySeason(Integer season);
}
