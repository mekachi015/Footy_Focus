package com.example.FootyFocus.repository;

import com.example.FootyFocus.entity.TeamStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamStatisticsRepo extends JpaRepository<TeamStatistics, Long> {
}
