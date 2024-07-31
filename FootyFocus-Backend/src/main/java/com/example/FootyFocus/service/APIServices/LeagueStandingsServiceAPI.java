package com.example.FootyFocus.service.APIServices;

import com.example.FootyFocus.entity.LeagueStandings;
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
public class LeagueStandingsServiceAPI {

    private static final String API_URL = "http://api.football-data.org/v4/competitions/{leagueCode}/standings?season={season}";
    private static final String API_TOKEN = "5576d3daf0c143b79ccbb7c9a1b22607";

    public List<LeagueStandings> fetchStandingsFromApi(String leagueCode, int season) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", API_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                API_URL, HttpMethod.GET, entity, Map.class, leagueCode, season);
        Map<String, Object> body = response.getBody();

        if (body == null) {
            throw new RuntimeException("Response body is null");
        }

        return mapToLeagueStandings(body);
    }

    private List<LeagueStandings> mapToLeagueStandings(Map<String, Object> body) {
        List<LeagueStandings> leagueStandingsList = new ArrayList<>();

        Map<String, Object> competition = (Map<String, Object>) body.get("competition");
        Map<String, Object> season = (Map<String, Object>) body.get("season");
        List<Map<String, Object>> standings = (List<Map<String, Object>>) body.get("standings");

        for (Map<String, Object> standing : standings) {
            List<Map<String, Object>> table = (List<Map<String, Object>>) standing.get("table");

            for (Map<String, Object> teamStanding : table) {
                LeagueStandings leagueStandings = new LeagueStandings();
                Map<String, Object> team = (Map<String, Object>) teamStanding.get("team");

                leagueStandings.setCompetitionName((String) competition.get("name"));
                leagueStandings.setCompetitionEmblem((String) competition.get("emblem"));
                leagueStandings.setSeasonStartDate((String) season.get("startDate"));
                leagueStandings.setSeasonEndDate((String) season.get("endDate"));
                leagueStandings.setCurrentMatchDay((int) season.get("currentMatchday"));

                leagueStandings.setPosition((int) teamStanding.get("position"));
                leagueStandings.setTeamName((String) team.get("name"));
                leagueStandings.setTeamCrest((String) team.get("crest"));
                leagueStandings.setTeamShortName((String) team.get("shortName"));
                leagueStandings.setMatchesPlayed((int) teamStanding.get("playedGames"));
                leagueStandings.setTeamForm((String) teamStanding.get("form"));
                leagueStandings.setGamesWon((int) teamStanding.get("won"));
                leagueStandings.setGamesLost((int) teamStanding.get("lost"));
                leagueStandings.setGamesDrew((int) teamStanding.get("draw"));
                leagueStandings.setNoOfPoints((int) teamStanding.get("points"));
                leagueStandings.setGoalDifference((int) teamStanding.get("goalDifference"));

                leagueStandingsList.add(leagueStandings);
            }
        }

        return leagueStandingsList;
    }
}
