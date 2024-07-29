package com.example.FootyFocus.service;

import com.example.FootyFocus.entity.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ApiService {

    // A service responsible for getting the information from an external API

    // Specifically, information for the persons table
    private static final String API_URL = "http://api.football-data.org/v4/persons/";
    private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";


    private  static  final String SEARCH_API_URL = "http://api.football-data.org/v4/persons/";

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

//    public Person searchPersonByName(String name) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-Auth-Token", API_TOKEN);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // Build the search URL with the player's name as a query parameter
//        String url = SEARCH_API_URL + "?name=" + name;
//
//        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map[].class);
//        Map[] body = response.getBody();
//
//        if (body == null || body.length == 0) {
//            throw new RuntimeException("No player found with the given name");
//        }
//
//        Map<String, Object> playerData = body[0]; // Assume we take the first result for simplicity
//
//        Person person = new Person();
//        person.setName((String) playerData.get("name"));
//        person.setDateOfBirth((String) playerData.get("dateOfBirth"));
//        person.setNationality((String) playerData.get("nationality"));
//        person.setSection((String) playerData.get("section"));
//        person.setShirtNumber((Integer) playerData.get("shirtNumber"));
//
//        Map<String, Object> currentTeam = (Map<String, Object>) playerData.get("currentTeam");
//        if (currentTeam != null) {
//            person.setTeamName((String) currentTeam.get("name"));
//            person.setTeamCode((String) currentTeam.get("code"));
//            person.setTeamFlag((String) currentTeam.get("flag"));
//        }
//
//        return person;
//    }

//    //Searches by name and returns a id
//    public Long searchPersonByNameReturnId(String name) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-Auth-Token", API_TOKEN);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // Build the search URL with the player's name as a query parameter
//        String url = SEARCH_API_URL + "?name=" + name;
//
//        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map[].class);
//        Map[] body = response.getBody();
//
//        if (body == null || body.length == 0) {
//            throw new RuntimeException("No player found with the given name");
//        }
//
//        Map<String, Object> playerData = body[0]; // Assume we take the first result for simplicity
//
//        return ((Number) playerData.get("id")).longValue();
//    }

    public Long searchPersonByName(String name) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", API_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String searchUrl = API_URL + "?name=" + name;
        ResponseEntity<List> response = restTemplate.exchange(searchUrl, HttpMethod.GET, entity, List.class);
        List<Map<String, Object>> body = response.getBody();

        if (body == null || body.isEmpty()) {
            throw new RuntimeException("No player found with name: " + name);
        }

        Map<String, Object> player = body.get(0); // Assuming the first match is taken
        return ((Number) player.get("id")).longValue();
    }

    public Person fetchPersonByName(String name) {
        Long id = searchPersonByName(name);
        return fetchPersonFromApi(id);
    }
}


