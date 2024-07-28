//package com.example.FootyFocus.service;
//
//import com.example.FootyFocus.entity.CompWinners;
//import com.example.FootyFocus.repository.CompWinnersRepo;
//import com.fasterxml.jackson.databind.JsonNode;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//public class CompWinnersApiService {
//
//    // a service for getting competition data from the api
//
//    // Specifically, information for the persons table
//    private static final String API_URL = "http://api.football-data.org/v4/competitions/";
//    private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";
//
//    public CompWinners fetchingWinnersFromApi(String leagueCode){
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-Auth-Token", API_TOKEN);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<Map> response = restTemplate.exchange(API_URL + leagueCode, HttpMethod.GET, entity, Map.class);
//        Map<String,Object> body = response.getBody();
//
//
//        CompWinners compWinners = new CompWinners();
//        Map<String,Object> winner = (Map<String, Object>) body.get("winner");
//        if(winner != null) {
//            compWinners.setWinnerName((String) body.get("name"));
//            compWinners.setwShortName((String) body.get("tla"));
//            compWinners.setWinnersCrest((String) body.get("crest"));
//            compWinners.setWinnerVenue((String) body.get("venue"));
//        }
//
//        compWinners.setStartDate((String) body.get("startDate"));
//        compWinners.setEndDate((String) body.get("endDate"));
//
//        return compWinners;
//
//    }
//
//}


//package com.example.FootyFocus.service;
//
//import com.example.FootyFocus.entity.CompWinners;
//import com.example.FootyFocus.repository.CompWinnersRepo;
//import com.fasterxml.jackson.databind.JsonNode;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.client.RestClientException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CompWinnersApiService {
//
//    @Value("${football.api.token}")
//    private String apiToken;
//
//    private final CompWinnersRepo compWinnersRepo;
//    private final RestTemplate restTemplate;
//
//    public CompWinnersApiService(CompWinnersRepo compWinnersRepo, RestTemplate restTemplate) {
//        this.compWinnersRepo = compWinnersRepo;
//        this.restTemplate = restTemplate;
//    }
//
//    private static final String API_URL = "http://api.football-data.org/v4/competitions/";
//
//    public List<CompWinners> fetchingWinnersFromApi(String leagueCode) {
//        List<CompWinners> compWinnersList = new ArrayList<>();
//        try {
//            String url = API_URL + leagueCode;
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("X-Auth-Token", apiToken);
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//
//            ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
//            JsonNode body = response.getBody();
//
//            if (body != null) {
//                JsonNode seasons = body.get("seasons");
//                if (seasons != null) {
//                    for (JsonNode season : seasons) {
//                        JsonNode winner = season.get("winner");
//                        if (winner != null) {
//                            CompWinners compWinners = new CompWinners();
//                            compWinners.setLeagueCode(leagueCode);
//                            compWinners.setStartDate(season.has("startDate") ? season.get("startDate").asText() : null);
//                            compWinners.setEndDate(season.has("endDate") ? season.get("endDate").asText() : null);
//                            compWinners.setWinnerName(winner.has("name") ? winner.get("name").asText() : null);
//                            compWinners.setwShortName(winner.has("tla") ? winner.get("tla").asText() : null);
//                            compWinners.setWinnersCrest(winner.has("crest") ? winner.get("crest").asText() : null);
//                            compWinners.setWinnerVenue(winner.has("venue") ? winner.get("venue").asText() : null);
//
//                            compWinnersRepo.save(compWinners);
//                            compWinnersList.add(compWinners);
//                        }
//                    }
//                }
//            }
//        } catch (RestClientException e) {
//            System.err.println("API request error: " + e.getMessage());
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.err.println("Unexpected error: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return compWinnersList;
//    }
//}

package com.example.FootyFocus.service;

import com.example.FootyFocus.entity.CompWinners;
import com.example.FootyFocus.exception.InvalidLeagueCodeException;
import com.example.FootyFocus.repository.CompWinnersRepo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CompWinnersApiService {

    @Value("${football.api.token}")
    private String apiToken;

    private final CompWinnersRepo compWinnersRepo;
    private final RestTemplate restTemplate;

    public CompWinnersApiService(CompWinnersRepo compWinnersRepo, RestTemplate restTemplate) {
        this.compWinnersRepo = compWinnersRepo;
        this.restTemplate = restTemplate;
    }

    private static final String API_URL = "http://api.football-data.org/v4/competitions/";

    public List<CompWinners> fetchingWinnersFromApi(String leagueCode) {
        List<CompWinners> compWinnersList = new ArrayList<>();
        try {
            String url = API_URL + leagueCode;
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Auth-Token", apiToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
            JsonNode body = response.getBody();

            if (body != null) {
                System.out.println("API Response: " + body.toString());
                JsonNode seasons = body.get("seasons");
                if (seasons != null) {
                    for (JsonNode season : seasons) {
                        JsonNode winner = season.get("winner");
                        if (winner != null) {
                            CompWinners compWinners = new CompWinners();
                            compWinners.setLeagueCode(leagueCode);
                            compWinners.setStartDate(season.has("startDate") ? season.get("startDate").asText() : null);
                            compWinners.setEndDate(season.has("endDate") ? season.get("endDate").asText() : null);
                            compWinners.setWinnerName(winner.has("name") ? winner.get("name").asText() : null);
                            compWinners.setwShortName(winner.has("tla") ? winner.get("tla").asText() : null);
                            compWinners.setWinnersCrest(winner.has("crest") ? winner.get("crest").asText() : null);
                            compWinners.setWinnerVenue(winner.has("venue") ? winner.get("venue").asText() : null);

                            compWinnersRepo.save(compWinners);
                            compWinnersList.add(compWinners);
                        }
                    }
                } else {
                    System.out.println("No seasons data found in the response.");
                }
            } else {
                System.out.println("Response body is null.");
            }
        } catch (RestClientException e) {
            System.err.println("API request error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
        return compWinnersList;
    }

    public class LeagueCodeValidator {

        private static final Set<String> VALID_LEAGUE_CODES = Set.of("PL","SA","DED", "BL1", "FL1", "PD", "CL","EC");

        public static void validate(String leagueCode) throws InvalidLeagueCodeException {
            if (!VALID_LEAGUE_CODES.contains(leagueCode.toUpperCase())){
                throw new InvalidLeagueCodeException("Invalid leage code:" + leagueCode);
            }
        }
    }

}
