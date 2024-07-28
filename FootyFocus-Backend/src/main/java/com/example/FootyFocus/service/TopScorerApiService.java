package com.example.FootyFocus.service;

import com.example.FootyFocus.dto.ApiResponse;
import com.example.FootyFocus.entity.TopScorer;
import com.example.FootyFocus.repository.TopScorerRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class TopScorerApiService {

    @Value("${football.api.token}")
    private String apiToken;

    private final TopScorerRepo topScorerRepo;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL = "http://api.football-data.org/v4/competitions";

    public TopScorerApiService(TopScorerRepo topScorerRepo) {
        this.topScorerRepo = topScorerRepo;
    }

    public void fetchAndSaveTopScorers(String leagueCode, String season) {
        String apiUrl = String.format("%s/%s/scorers?season=%s", BASE_URL, leagueCode, season);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, ApiResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            List<TopScorer> topScorers = response.getBody().getTopScorers();
            topScorerRepo.saveAll(topScorers);
        } else {
            throw new RuntimeException("Failed to fetch top scorers from API");
        }
    }
}
