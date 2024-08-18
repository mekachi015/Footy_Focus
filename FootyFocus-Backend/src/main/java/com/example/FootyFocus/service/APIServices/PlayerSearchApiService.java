package com.example.FootyFocus.service.APIServices;

import org.springframework.stereotype.Service;
import com.example.FootyFocus.entity.PlayerSearch;
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
public class PlayerSearchApiService {

    private static final String API_URL = "https://v3.football.api-sports.io/players";
    private static final String API_TOKEN = "fc335a35da3e025cf91907aa4ce82245";

    public List<PlayerSearch> fetchPlayersFromApi(String searchQuery, int league, int season) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", API_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                API_URL, HttpMethod.GET, entity, Map.class, searchQuery, league, season);
        Map<String, Object> body = response.getBody();

        if (body == null) {
            throw new RuntimeException("Response body is null");
        }

        return mapToPlayerSearchInfo(body);
    }

    private List<PlayerSearch> mapToPlayerSearchInfo(Map<String, Object> body) {
        List<PlayerSearch> playerSearchList = new ArrayList<>();

        // Get the list of players
        List<Map<String, Object>> players = (List<Map<String, Object>>) body.get("response");
        if (players == null) {
            throw new RuntimeException("Players list is null");
        }

        for (Map<String, Object> playerData : players) {
            PlayerSearch playerSearch = new PlayerSearch();

            // Map player data
            Map<String, Object> player = (Map<String, Object>) playerData.get("player");
            if (player == null) {
                continue; // Skip if player data is missing
            }

            Map<String, Object> birth = (Map<String, Object>) player.get("birth");
            Map<String, Object> team = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("team");
            Map<String, Object> league = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("league");
            Map<String, Object> games = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("games");
            Map<String, Object> shots = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("shots");
            Map<String, Object> goals = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("goals");
            Map<String, Object> passes = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("passes");
            Map<String, Object> tackles = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("tackles");
            Map<String, Object> duels = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("duels");
            Map<String, Object> dribbles = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("dribbles");
            Map<String, Object> fouls = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("fouls");
            Map<String, Object> cards = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("cards");
            Map<String, Object> penalty = (Map<String, Object>) ((List<Map<String, Object>>) playerData.get("statistics")).get(0).get("penalty");

            // Map fields from the player data
            playerSearch.setFirstName((String) player.get("firstname"));
            playerSearch.setLastName((String) player.get("lastname"));
            playerSearch.setPlayerAge(((Number) player.get("age")).intValue());
            playerSearch.setDateOfBirth((String) birth.get("date"));
            playerSearch.setPlaceOfBirth((String) birth.get("place"));
            playerSearch.setCountryOfBirth((String) birth.get("country"));
            playerSearch.setHeight(Integer.parseInt(((String) player.get("height")).replace(" cm", "")));
            playerSearch.setWeight(Integer.parseInt(((String) player.get("weight")).replace(" kg", "")));
            playerSearch.setInjuredStatus((Boolean) player.get("injured"));
            playerSearch.setPlayerPhoto((String) player.get("photo"));

            if (team != null) {
                playerSearch.setTeamName((String) team.get("name"));
                playerSearch.setTeamLogo((String) team.get("logo"));
            }

            if (league != null) {
                playerSearch.setLeagueName((String) league.get("name"));
                playerSearch.setLeagueLogo((String) league.get("logo"));
            }

            if (games != null) {
                playerSearch.setNoAppearences(((Number) games.get("appearences")).intValue());
                playerSearch.setMinutesPlayed(((Number) games.get("minutes")).intValue());
                playerSearch.setPosition((String) games.get("position"));
                playerSearch.setRating(Float.parseFloat((String) games.get("rating")));
                playerSearch.setCaptain((Boolean) games.get("captain"));
            }

            if (shots != null) {
                playerSearch.setShotsTaken(((Number) shots.get("total")).intValue());
                playerSearch.setShotsOnTarget(((Number) shots.get("on")).intValue());
            }

            if (goals != null) {
                playerSearch.setTotalGoalsScored(((Number) goals.get("total")).intValue());
                playerSearch.setGoalsConceeded(((Number) goals.get("conceded")).intValue());
                playerSearch.setNoAssits(((Number) goals.get("assists")).intValue());
                playerSearch.setNoSaves((String) goals.get("saves"));
            }

            if (passes != null) {
                playerSearch.setTotalPasses(((Number) passes.get("total")).intValue());
                playerSearch.setTotalKeyPasses(((Number) passes.get("key")).intValue());
                playerSearch.setPassingAccuracy(((Number) passes.get("accuracy")).intValue());
            }

            if (tackles != null) {
                playerSearch.setTotalTackles(((Number) tackles.get("total")).intValue());
                playerSearch.setBlocks(((Number) tackles.get("blocks")).intValue());
                playerSearch.setInterceptions(((Number) tackles.get("interceptions")).intValue());
            }

            if (duels != null) {
                playerSearch.setTotalDuels(((Number) duels.get("total")).intValue());
                playerSearch.setDuelsWon(((Number) duels.get("won")).intValue());
            }

            if (dribbles != null) {
                playerSearch.setDribbleAttempts(((Number) dribbles.get("attempts")).intValue());
                playerSearch.setSuccessfullDribbles(((Number) dribbles.get("success")).intValue());
            }

            if (fouls != null) {
                playerSearch.setFoulsDrawn(((Number) fouls.get("drawn")).intValue());
                playerSearch.setFoulsCommitted(((Number) fouls.get("committed")).intValue());
            }

            if (cards != null) {
                playerSearch.setNoYellowCards(((Number) cards.get("yellow")).intValue());
                playerSearch.setNoYellowRedCards(((Number) cards.get("yellowred")).intValue());
                playerSearch.setNoRedCards(((Number) cards.get("red")).intValue());
            }

            if (penalty != null) {
                playerSearch.setPenaltiesWon(((Number) penalty.get("won")).intValue());
                playerSearch.setPenaltiesCommited(((Number) penalty.get("commited")).intValue());
                playerSearch.setPenaltiesScored(((Number) penalty.get("scored")).intValue());
                playerSearch.setPenaltiesMissed(((Number) penalty.get("missed")).intValue());
                playerSearch.setPenalitesSaved(((Number) penalty.get("saved")).intValue());
            }

            playerSearchList.add(playerSearch);
        }

        return playerSearchList;
    }

}
