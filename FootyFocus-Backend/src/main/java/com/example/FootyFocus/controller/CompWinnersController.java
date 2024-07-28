package com.example.FootyFocus.controller;

import com.example.FootyFocus.entity.CompWinners;
import com.example.FootyFocus.exception.InvalidLeagueCodeException;
import com.example.FootyFocus.service.CompWinnersApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v4/competitions/")
public class CompWinnersController {

    @Autowired
    private CompWinnersApiService compWinnersApiService;

    @GetMapping("/{leagueCode}")
    public List<CompWinners> getLast15SeasonWinners(@PathVariable String leagueCode) {
        return compWinnersApiService.fetchingWinnersFromApi(leagueCode);

    }

    @ExceptionHandler(InvalidLeagueCodeException.class)
    public ResponseEntity<String> handleInvalidLeagueCodeException(InvalidLeagueCodeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
