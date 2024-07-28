package com.example.FootyFocus.service;

import com.example.FootyFocus.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPerson(); //a method that retrieves all the players

    Person getPersonById(Long id); // a method that gets a specific player

    Person savePerson(Person person); //a method that saves the player to the watchlist

    void deletePerson(Long id); //a method that is responsible for removing a player from the list
}
