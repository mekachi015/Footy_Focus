package com.example.FootyFocus.service;

import com.example.FootyFocus.entity.Person;
import com.example.FootyFocus.exception.InvalidLeagueCodeException;

import java.util.List;
import java.util.Set;

public interface PersonService {

    List<Person> getAllPerson(); //a method that retrieves all the players

    Person getPersonById(Long id); // a method that gets a specific player

    Person savePerson(Person person); //a method that saves the player to the watchlist

    void deletePerson(Long id); //a method that is responsible for removing a player from the list

    class LeagueCodeValidator {

            private static final Set<String> VALID_LEAGUE_CODES = Set.of("PL","SA","DED", "BL1", "FL1", "PD", "CL","EC");

            public static void validate(String leagueCode) throws InvalidLeagueCodeException {
                if (!VALID_LEAGUE_CODES.contains(leagueCode.toUpperCase())){
                    throw new InvalidLeagueCodeException("Invalid league code:" + leagueCode);
                }
            }
        }
}
