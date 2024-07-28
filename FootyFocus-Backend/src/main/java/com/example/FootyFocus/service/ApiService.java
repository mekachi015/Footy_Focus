package com.example.FootyFocus.service;

import com.example.FootyFocus.entity.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ApiService {

    // A service responsible for getting the information from an external API

    // Specifically, information for the persons table
    private static final String API_URL = "http://api.football-data.org/v4/persons/";
    private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";



    public Person fetchPersonFromApi(Long id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", API_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(API_URL + id, HttpMethod.GET, entity, Map.class);
        Map<String, Object> body = response.getBody();

        if (body == null) {
            throw new RuntimeException("Response body is null");
        }

        Person person = new Person();
        person.setName((String) body.get("name"));
        person.setDateOfBirth((String) body.get("dateOfBirth"));
        person.setNationality((String) body.get("nationality"));
        person.setSection((String) body.get("section"));
        person.setShirtNumber((Integer) body.get("shirtNumber"));

        Map<String, Object> currentTeam = (Map<String, Object>) body.get("currentTeam");
        if (currentTeam != null) {
            person.setTeamName((String) currentTeam.get("name"));
            person.setTeamCode((String) currentTeam.get("code"));
            person.setTeamFlag((String) currentTeam.get("flag"));
        }

        return person;
    }


}
