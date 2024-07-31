package com.example.FootyFocus.service.APIServices;

import com.example.FootyFocus.entity.detailedPlayer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Map;

@Service
public class detailedPlayerAPIService {

    private static final String API_URL = "http://api.football-data.org/v4/persons/";
    private static final String SEARCH_API_URL = "http://api.football-data.org/v4/person";
    private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";

    public detailedPlayer fetchPlayerInfoFromAPI(Long id){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers =  new HttpHeaders();
        headers.set("X-Auth-Token", API_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        HttpEntity<Map> response = restTemplate.exchange(API_URL + id, HttpMethod.GET, entity, Map.class);
        Map<String, Object> body = response.getBody();

        if(body ==  null){
            throw new RuntimeException("Reponse body is null");
        }

        detailedPlayer playerInfo = new detailedPlayer();
        playerInfo.setId(id);
        playerInfo.setName((String) body.get("name"));
        playerInfo.setDateOfBirth((String) body.get("dateOfBirth"));
        playerInfo.setNationality((String) body.get("nationality"));
//        playerInfo.setShirtNumber((int) body.get("shirtNumber"));
        playerInfo.setSection((String) body.get("position"));

        // Current team information
        Map<String, Object> currentTeam = (Map<String, Object>) body.get("currentTeam");
        if (currentTeam != null) {
            playerInfo.setTeamName((String) currentTeam.get("name"));
            playerInfo.setTeamCrest((String) currentTeam.get("crest"));
            playerInfo.setTeamVenue((String) currentTeam.get("venue"));

            // Current team area information
            Map<String, Object> area = (Map<String, Object>) currentTeam.get("area");
            if (area != null) {
                playerInfo.setAreaName((String) area.get("name"));
                playerInfo.setCountryflag((String) area.get("flag"));
            }
        }

         //Contract information
        Map<String, Object> contract = (Map<String, Object>) body.get("contract");
        if (contract != null) {
            playerInfo.setContractStartDate((String) contract.get("startDate"));
            playerInfo.setContractEndDate((String) contract.get("endDate"));
        }

        return playerInfo;

    }

    public Long searchPlayerByName(String name){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", API_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        HttpEntity<Map> response = restTemplate.exchange(SEARCH_API_URL, HttpMethod.GET, entity, Map.class, name);

        Map<String, Object> body  = response.getBody();

        if (body == null || body.isEmpty()) {
            throw new RuntimeException("No player with this name is found" + name);
        }

        Map<String ,Object> playerData = (Map<String, Object>) ((Map<String, Object>) body.get("players")).get(0);

        return (Long) playerData.get("id");
//        return  mapToDetailedPlayer(playerData);
    }

    private detailedPlayer mapToDetailedPlayer(Map<String, Object> body){

        detailedPlayer playerInfo = new detailedPlayer();
        playerInfo.setName((String) body.get("name"));
        playerInfo.setDateOfBirth((String) body.get("dateOfBirth"));
        playerInfo.setNationality((String) body.get("nationality"));
        playerInfo.setShirtNumber((int) body.get("shirtNumber"));
        playerInfo.setSection((String) body.get("position"));

        Map<String, Object> currentTeam = (Map<String, Object>) body.get("currentTeam");

        if(currentTeam != null){

            playerInfo.setTeamName((String) currentTeam.get("name"));
            playerInfo.setTeamCrest((String) currentTeam.get("crest"));
            playerInfo.setTeamVenue((String) currentTeam.get("venue"));

            Map<String,Object> area = (Map<String, Object>)  currentTeam.get("area");
            if(area != null){
                playerInfo.setAreaName((String) area.get("name"));
                playerInfo.setCountryflag((String) area.get("flag"));
            }
        }

        Map<String, Object> contract = (Map<String, Object>)body. get("contract");

        if (contract != null){
            playerInfo.setContractStartDate((String)  contract.get("startDate"));
            playerInfo.setContractEndDate((String) contract.get("endDate"));
        }


        return playerInfo;
    }


}
