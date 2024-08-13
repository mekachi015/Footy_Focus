package com.example.FootyFocus.service.APIServices;


import com.example.FootyFocus.entity.Matches;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MatchesApiService {
    private static final String API_URL = "http://api.football-data.org/v4/competitions/{competitionCode}/matches?matchday={matchday}&season={season}";
    private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";

    public List<Matches> fetchMatchesFromApi(String competitionCode, int matchday, int season) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", API_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                API_URL, HttpMethod.GET, entity, Map.class, competitionCode, matchday, season);
        Map<String, Object> body = response.getBody();

        if (body == null) {
            throw new RuntimeException("Response body is null");
        }

        return mapToMatchInfo(body);
    }

    private List<Matches> mapToMatchInfo(Map<String, Object> body) {
        List<Matches> matchInfoList = new ArrayList<>();

        // Retrieve and cast the 'competition' and 'matches' from the response body
        Map<String, Object> competition = (Map<String, Object>) body.get("competition");
        List<Map<String, Object>> matches = (List<Map<String, Object>>) body.get("matches");

        for (Map<String, Object> match : matches) {
            Matches matchInfo = new Matches();

            // Cast to Maps for nested JSON objects
            Map<String, Object> homeTeam = (Map<String, Object>) match.get("homeTeam");
            Map<String, Object> awayTeam = (Map<String, Object>) match.get("awayTeam");
            Map<String, Object> score = (Map<String, Object>) match.get("score");
            List<Map<String, Object>> referees = (List<Map<String, Object>>) match.get("referees");
            // Extract competition details
            matchInfo.setCompName((String) competition.get("name"));
            matchInfo.setCompEmblem((String) competition.get("emblem"));

            // Extract match details
            matchInfo.setId(((Number) match.get("id")).longValue());
            matchInfo.setUtcDate((String) match.get("utcDate"));

            // Extract home team details
            Map<String, Object> homeTeamMap = (Map<String, Object>) homeTeam;
            matchInfo.setHomeTeamName((String) homeTeamMap.get("name"));
            matchInfo.setHomeTeamCrest((String) homeTeamMap.get("crest"));

            // Extract away team details
            Map<String, Object> awayTeamMap = (Map<String, Object>) awayTeam;
            matchInfo.setAwayTeamName((String) awayTeamMap.get("name"));
            matchInfo.setAwayTeamCrest((String) awayTeamMap.get("crest"));

            // Extract scores
            Map<String, Object> fullTimeScores = (Map<String, Object>) score.get("fullTime");
            matchInfo.setHomeFullScore(((Number) fullTimeScores.get("home")).intValue());
            matchInfo.setAwayFullScore(((Number) fullTimeScores.get("away")).intValue());

            Map<String, Object> halfTimeScores = (Map<String, Object>) score.get("halfTime");
            matchInfo.setHomeHalfScore(((Number) halfTimeScores.get("home")).intValue());
            matchInfo.setAwayHalfScore(((Number) halfTimeScores.get("away")).intValue());

            //gets a list of referee objects
            if (!referees.isEmpty()) {
                Map<String, Object> referee = referees.get(0);
                matchInfo.setRefName((String) referee.get("name"));
                matchInfo.setRefNationality((String) referee.get("nationality"));
            }


            matchInfoList.add(matchInfo);
        }

        return matchInfoList;
    }

}
