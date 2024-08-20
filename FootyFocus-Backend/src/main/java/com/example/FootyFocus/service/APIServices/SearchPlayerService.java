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

                    playerInfo.setPosition((String) games.get("position"));
                    playerInfo.setAppearances(games.get("appearences") != null ? (int) games.get("appearences") : 0);
                    playerInfo.setCaptain(games.get("captain") != null ? (boolean) games.get("captain") : false);

                    playerInfo.setGoals(goals.get("total") != null ? (int) goals.get("total") : 0);
                    playerInfo.setAssists(goals.get("assists") != null ? (int) goals.get("assists") : 0);
                    playerInfo.setGoalsConceded(goals.get("conceded") != null ? (int) goals.get("conceded") : 0);
                    playerInfo.setSaves(goals.get("saves") != null ? (int) goals.get("saves") : 0);

                    playerInfo.setTotalPasses(passes.get("total") != null ? (int) passes.get("total") : 0);
                    playerInfo.setKeyPasses(passes.get("key") != null ? (int) passes.get("key") : 0);
                    playerInfo.setPassAccuracy(passes.get("accuracy") != null ? (int) passes.get("accuracy") : 0);

                    playerInfo.setTotalTackles(tackles.get("total") != null ? (int) tackles.get("total") : 0);
                    playerInfo.setBlocks(tackles.get("blocks") != null ? (int) tackles.get("blocks") : 0);
                    playerInfo.setInterceptions(tackles.get("interceptions") != null ? (int) tackles.get("interceptions") : 0);

                    playerInfo.setTotalDuels(duels.get("total") != null ? (int) duels.get("total") : 0);
                    playerInfo.setDuelsWon(duels.get("won") != null ? (int) duels.get("won") : 0);

                    playerInfo.setDribbleAttempts(dribbles.get("attempts") != null ? (int) dribbles.get("attempts") : 0);
                    playerInfo.setSuccessfulDribbles(dribbles.get("success") != null ? (int) dribbles.get("success") : 0);

                    playerInfo.setFoulsDrawn(foul.get("drawn") != null ? (int) foul.get("drawn") : 0);
                    playerInfo.setFoulsCommitted(foul.get("committed") != null ? (int) foul.get("committed") : 0);

                    playerInfo.setYellowCards(cards.get("yellow") != null ? (int) cards.get("yellow") : 0);
                    playerInfo.setYellowRedCards(cards.get("yellowred") != null ? (int) cards.get("yellowred") : 0);
                    playerInfo.setRedCards(cards.get("red") != null ? (int) cards.get("red") : 0);

                    playerInfo.setPenaltiesWon(penalty.get("won") != null ? (int) penalty.get("won") : 0);
                    playerInfo.setPenaltiesCommitted(penalty.get("committed") != null ? (int) penalty.get("committed") : 0);
                    playerInfo.setPenaltiesScored(penalty.get("scored") != null ? (int) penalty.get("scored") : 0);
                    playerInfo.setPenaltiesMissed(penalty.get("missed") != null ? (int) penalty.get("missed") : 0);
                    playerInfo.setPenaltiesSaved(penalty.get("saved") != null ? (int) penalty.get("saved") : 0);

                    playerInfoList.add(playerInfo);
                }
            }
        } else {
            System.out.println("No 'response' field found or it is not a list.");
        }

        return playerInfoList;
    }

}
