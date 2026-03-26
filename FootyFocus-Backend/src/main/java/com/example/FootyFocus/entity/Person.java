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

@Table(name = "player")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // a field that stores the name of the player

    private String dateOfBirth; // stores player birthdate

    private String nationality; // stores the players home nation

    private String section; // stores the players position

    private int shirtNumber; // Stores the players jersey number

    private String teamName; // stores the name of the national team

    private String teamCode; // stores the team name but abbreviated

    private String teamFlag; // store a link with the image of the teams flag


}
