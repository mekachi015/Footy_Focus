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

                    playerInfo.setGoals((Integer) goals.get("total"));
                    playerInfo.setAssists((Integer) goals.get("assists"));

                    playerInfoList.add(playerInfo);
                }
            }
        } else {
            System.out.println("No 'response' field found or it is not a list.");
        }

        return playerInfoList;
    }

}
