package com.example.FootyFocus.service;

import com.example.FootyFocus.exception.InvalidLeagueCodeException;

import java.util.Set;


public class LeagueCodeValidator {

        private static final Set<String> VALID_LEAGUE_CODES = Set.of("PL","SA","DED", "BL1", "FL1", "PD", "CL","EC");

        public static void validate(String leagueCode) throws InvalidLeagueCodeException {
            if (!VALID_LEAGUE_CODES.contains(leagueCode.toUpperCase())){
                throw new InvalidLeagueCodeException("Invalid league code:" + leagueCode);
            }
        }
    }

