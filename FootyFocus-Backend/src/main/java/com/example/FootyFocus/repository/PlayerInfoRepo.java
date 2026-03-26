package com.example.FootyFocus.repository;

import com.example.FootyFocus.entity.PlayerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerInfoRepo extends JpaRepository<PlayerInfo, Long> {
}
