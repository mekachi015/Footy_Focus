package com.example.FootyFocus.service.APIServices;

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
        person.setId(id);
        person.setName((String) body.get("name"));
        person.setDateOfBirth((String) body.get("dateOfBirth"));
        person.setNationality((String) body.get("nationality"));
        person.setSection((String) body.get("section"));
        person.setShirtNumber((Integer) body.get("shirtNumber"));

        Map<String, Object> currentTeam = (Map<String, Object>) body.get("currentTeam");
        if (currentTeam != null) {
            person.setTeamName((String) currentTeam.get("name"));

            Map<String, Object> area = (Map<String, Object>) currentTeam.get("area");
            if (area != null) {
                person.setTeamFlag((String) area.get("flag"));
                person.setTeamCode((String) area.get("tla"));
            }
        }

        return person;
    }



    public List<Map<String, Object>> fetchAllPlayers() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", API_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, List.class);
        List<Map<String, Object>> body = response.getBody();

        if (body == null || body.isEmpty()) {
            throw new RuntimeException("No players found.");
        }

        return body;
    }

    public Long searchPersonByName(String name) {
        List<Map<String, Object>> players = fetchAllPlayers();

        for (Map<String, Object> player : players) {
            if (name.equalsIgnoreCase((String) player.get("name"))) {
                return ((Number) player.get("id")).longValue();
            }
        }

        throw new RuntimeException("No player found with name: " + name);
    }

    public Person fetchPersonByName(String name) {
        Long id = searchPersonByName(name);
        return fetchPersonFromApi(id);
    }

//    public void addPlayerToWatchlist(String name) {
//        Long id = searchPersonByName(name);
//        if (!watchlist.contains(id)) {
//            watchlist.add(id);
//        } else {
//            throw new RuntimeException("Player is already in the watchlist.");
//        }
//    }
//
//    public List<Long> getWatchlist() {
//        return watchlist;
//    }



}


