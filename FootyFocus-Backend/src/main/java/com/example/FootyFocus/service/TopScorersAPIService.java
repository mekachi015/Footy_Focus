package com.example.FootyFocus.service;

import com.example.FootyFocus.entity.TopGoalScorers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Service
//public class TopScorersAPIService {

//    private static final String API_URL = "http://api.football-data.org/v4/competitions/{leagueCode}/scorers?season={season}";
//    private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";


//    public TopGoalScorers fetchTopScorerFromApi(String leagueCode, int season){
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-Auth-Token", API_TOKEN);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<Map> response = restTemplate.exchange(API_URL + leagueCode + season, HttpMethod.GET, entity, Map.class);
//        Map<String,Object> body =  response.getBody();
//
//        if (body == null){
//            throw new RuntimeException("Response body is null");
//        }
//
//        TopGoalScorers topGoalScorers = new TopGoalScorers();
//        topGoalScorers.setName((String) body.get("name"));
//        topGoalScorers.setNationality((String) body.get("nationality"));
//        topGoalScorers.setSection((String) body.get("section"));
//        topGoalScorers.setMatchesPlayed((int) body.get("playedMatches"));
//        topGoalScorers.setGoals((int) body.get("goals"));
//        topGoalScorers.setPenalties((int) body.get("assists"));
//        topGoalScorers.setPenalties((int) body.get("penalties"));
//
//        return topGoalScorers;
//    }
@Service
    public class TopScorersAPIService {

        private static final String API_URL = "http://api.football-data.org/v4/competitions/{leagueCode}/scorers?season={season}";
        private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";

        public List<TopGoalScorers> fetchTopScorerFromApi(String leagueCode, int season) {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Auth-Token", API_TOKEN);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            try {
                ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, Map.class, leagueCode, season);
                Map<String, Object> body = response.getBody();

                if (body == null) {
                    throw new RuntimeException("Response body is null");
                }

                List<Map<String, Object>> scorersList = (List<Map<String, Object>>) body.get("scorers");
                List<TopGoalScorers> topGoalScorersList = new ArrayList<>();

                for (Map<String, Object> scorerMap : scorersList) {
                    Map<String, Object> player = (Map<String, Object>) scorerMap.get("player");
                    Map<String, Object> team = (Map<String, Object>) scorerMap.get("team");
                    Integer playedMatches = (Integer) scorerMap.get("playedMatches");
                    Integer goals = (Integer) scorerMap.get("goals");
                    Integer assists = (Integer) scorerMap.get("assists");
                    Integer penalties = (Integer) scorerMap.get("penalties");

                    TopGoalScorers topGoalScorers = new TopGoalScorers();
                    topGoalScorers.setName((String) player.get("name"));
                    topGoalScorers.setNationality((String) player.get("nationality"));
                    topGoalScorers.setSection((String) team.get("name"));
                    topGoalScorers.setMatchesPlayed(playedMatches);
                    topGoalScorers.setGoals(goals);
                    topGoalScorers.setAssists(assists);
                    topGoalScorers.setPenalties(penalties);

                    topGoalScorersList.add(topGoalScorers);
                }

                return topGoalScorersList;
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                throw new RuntimeException("Failed to fetch top scorers from API", e);
            }
        }

}
