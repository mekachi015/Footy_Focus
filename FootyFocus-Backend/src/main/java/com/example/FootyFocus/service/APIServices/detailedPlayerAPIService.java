package com.example.FootyFocus.service.APIServices;

import com.example.FootyFocus.entity.detailedPlayer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Date;
import java.util.Map;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

@Service
public class detailedPlayerAPIService {

    private static final String API_URL = "http://api.football-data.org/v4/persons/";
    private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";

    public detailedPlayer fetchDetailedPlayerInfo(Long id){
       RestTemplate restTemplate = new RestTemplate();

       HttpHeaders headers = new HttpHeaders();
       headers.set("X-Auth-Token", API_TOKEN);

       HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(API_URL + id, HttpMethod.GET, entity, Map.class);

        Map<String, Object> body = response.getBody();

        if (body == null){
        throw new RuntimeException("Response body is null");
        }

        detailedPlayer playerInfo = new detailedPlayer();
        playerInfo.setId(id);
        playerInfo.setPlayerName((String)  body.get("name"));
        playerInfo.setDateOfBirth((String) body.get("dateOfBirth"));
        playerInfo.setNationality((String) body.get("nationality"));
        playerInfo.setSection((String) body.get("section"));
        playerInfo.setShirtNumber((int) body.get("shirtNumber"));



        Map<String, Object> currentTeam = (Map<String, Object>) body.get("currentTeam");
        if (currentTeam != null){
            playerInfo.setTeamName((String) currentTeam.get("name"));
            playerInfo.setTeamCrest((String) currentTeam.get("crest"));
            playerInfo.setTeamVenue((String) currentTeam.get("venue"));
        }



        return playerInfo;
    }


}
