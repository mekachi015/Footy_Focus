package com.example.FootyFocus.repository;

import com.example.FootyFocus.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
}
