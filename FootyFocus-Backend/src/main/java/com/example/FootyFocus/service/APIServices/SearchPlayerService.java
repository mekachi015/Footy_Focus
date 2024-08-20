package com.example.FootyFocus.service.APIServices;

import com.example.FootyFocus.entity.PlayerInfo;
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
public class SearchPlayerService {

    private static final String API_URL = "https://v3.football.api-sports.io/";
    private static final String API_TOKEN = "fc335a35da3e025cf91907aa4ce82245";

    public List<PlayerInfo> fetchPlayerInfoFromApi(String search, int league, int season) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", API_TOKEN);
        headers.set("x-apisports-host", "v3.football.api-sports.io");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = String.format("%splayers?search=%s&league=%d&season=%d", API_URL, search, league, season);

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> body = response.getBody();
            if (body == null) {
                throw new RuntimeException("Response body is null");
            }

            // Print the raw response body
            System.out.println("Response Body: " + body);

            List<PlayerInfo> players = mapToPlayerInformation(body);
            System.out.println("Mapped Player Info: " + players);

            return players;
        } else {
            throw new RuntimeException("Failed to fetch data: " + response.getStatusCode());
        }
    }

    private List<PlayerInfo> mapToPlayerInformation(Map<String, Object> body) {
        List<PlayerInfo> playerInfoList = new ArrayList<>();

        // Check if 'response' is present in the body and is a list
        Object responseObject = body.get("response");
        if (responseObject instanceof List) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) responseObject;

            for (Map<String, Object> response : responses) {
                Map<String, Object> player = (Map<String, Object>) response.get("player");
                List<Map<String, Object>> statistics = (List<Map<String, Object>>) response.get("statistics");

                if (player != null && !statistics.isEmpty()) {
                    Map<String, Object> stats = statistics.get(0);
                    Map<String, Object> team = (Map<String, Object>) stats.get("team");
                    Map<String, Object> games = (Map<String, Object>) stats.get("games");
                    Map<String, Object> goals = (Map<String, Object>) stats.get("goals");
                    Map<String, Object> passes = (Map<String, Object>) stats.get("passes");
                    Map<String, Object> tackles = (Map<String, Object>) stats.get("takcles");
                    Map<String, Object>  duels = (Map<String, Object>) stats.get("duels");
                    Map<String, Object> dribbles = (Map<String, Object>) stats.get("dribbles");
                    Map<String, Object> foul = (Map<String, Object>) stats.get("foul");
                    Map<String, Object> cards = (Map<String, Object>) stats.get("cards");
                    Map<String, Object> penalty = (Map<String, Object>) stats.get("penalty");


                    PlayerInfo playerInfo = new PlayerInfo();
                    playerInfo.setFirstname((String) player.get("firstname"));
                    playerInfo.setLastname((String) player.get("lastname"));
                    playerInfo.setAge((Integer) player.get("age"));

                    Map<String, Object> birth = (Map<String, Object>) player.get("birth");
                    playerInfo.setBirthDate((String) birth.get("date"));
                    playerInfo.setBirthPlace((String) birth.get("place"));
                    playerInfo.setBirthPlace((String) birth. get("country"));

                    playerInfo.setHeight((String) player.get("height"));
                    playerInfo.setWeight((String) player.get("weight"));
                    playerInfo.setPhoto((String) player.get("photo"));

                    playerInfo.setTeamName((String) team.get("name"));
                    playerInfo.setTeamLogo((String) team.get("logo"));

                    //playerInfo.setAppearances((Integer) games.get("appearances"));
                    playerInfo.setPosition((String) games.get("position"));
                    playerInfo.setAppearances((int) games.get("appearances"));
                    playerInfo.setCaptain((boolean) games.get("captain"));


                    playerInfo.setGoals((int) goals.get("total"));
                    playerInfo.setAssists((int) goals.get("assists"));
                    playerInfo.setGoalsConceded((int) goals.get("conceded"));
                    playerInfo.setSaves((int) goals.get("saves"));

                    playerInfo.setTotalPasses((int) passes.get("total"));
                    playerInfo.setKeyPasses((int) passes.get("key"));
                    playerInfo.setPassAccuracy((int) passes.get("accuracy"));

                    playerInfo.setTotalTackles((int) tackles.get("total"));
                    playerInfo.setBlocks((int) tackles.get("blocks"));
                    playerInfo.setInterceptions((int) tackles.get("interceptions"));

                    playerInfo.setTotalDuels((int) duels.get("total"));
                    playerInfo.setDuelsWon((int) duels.get("won"));

                    playerInfo.setDribbleAttempts((int) dribbles.get("attempts"));
                    playerInfo.setSuccessfulDribbles((int) dribbles.get("success"));

                    playerInfo.setFoulsDrawn((int) foul.get("drawn"));
                    playerInfo.setFoulsCommitted((int) foul.get("commited"));

                    playerInfo.setYellowCards((int) cards.get("yellow"));

                    playerInfoList.add(playerInfo);
                }
            }
        } else {
            System.out.println("No 'response' field found or it is not a list.");
        }

        return playerInfoList;
    }

}
