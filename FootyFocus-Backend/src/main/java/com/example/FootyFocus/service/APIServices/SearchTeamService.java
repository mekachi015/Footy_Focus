package com.example.FootyFocus.service.APIServices;

import com.example.FootyFocus.entity.TeamStatistics;
import com.example.FootyFocus.repository.TeamStatisticsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SearchTeamService {

    private static final String API_URL = "https://v3.football.api-sports.io/teams/statistics";
    private static final String API_TOKEN = "fc335a35da3e025cf91907aa4ce82245";

    @Autowired
    private TeamStatisticsRepo teamStatisticsRepository;

    public TeamStatistics fetchTeamStatisticsFromApi(int league, int season, int teamId) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", API_TOKEN);
        headers.set("x-rapidapi-host", "v3.football.api-sports.io");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = String.format("%s?league=%d&season=%d&team=%d", API_URL, league, season, teamId);

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody == null) {
                throw new RuntimeException("Response body is null");
            }

            System.out.println("Response Body: " + responseBody);

            // The data you need is inside the "response" key
            Map<String, Object> statistics = (Map<String, Object>) responseBody.get("response");

            return mapToTeamStatistics(statistics);
        } else {
            throw new RuntimeException("Failed to fetch data: " + response.getStatusCode());
        }
    }

    private TeamStatistics mapToTeamStatistics(Map<String, Object> statistics) {
        TeamStatistics teamStatistics = new TeamStatistics();

        // Mapping league details
        Map<String, Object> league = (Map<String, Object>) statistics.get("league");
        teamStatistics.setLeagueName(getStringValue(league, "name"));
        teamStatistics.setLeagueCountry(getStringValue(league, "country"));
        teamStatistics.setLeagueLogo(getStringValue(league, "logo"));
        teamStatistics.setLeagueFlag(getStringValue(league, "flag"));
        teamStatistics.setSeason(getIntValue(league, "season"));

        // Mapping team details
        Map<String, Object> team = (Map<String, Object>) statistics.get("team");
        teamStatistics.setTeamName(getStringValue(team, "name"));
        teamStatistics.setTeamLogo(getStringValue(team, "logo"));

        // Mapping form
        String form = getStringValue(statistics, "form");
        teamStatistics.setForm(form);

        // Mapping fixtures
        Map<String, Object> fixtures = (Map<String, Object>) statistics.get("fixtures");
        Map<String, Object> played = (Map<String, Object>) fixtures.get("played");
        Map<String, Object> wins = (Map<String, Object>) fixtures.get("wins");
        Map<String, Object> draws = (Map<String, Object>) fixtures.get("draws");
        Map<String, Object> loses = (Map<String, Object>) fixtures.get("loses");

        teamStatistics.setHomePlayed(getIntValue(played, "home"));
        teamStatistics.setAwayPlayed(getIntValue(played, "away"));
        teamStatistics.setTotalPlayed(getIntValue(played, "total"));

        teamStatistics.setHomeWins(getIntValue(wins, "home"));
        teamStatistics.setAwayWins(getIntValue(wins, "away"));
        teamStatistics.setTotalWins(getIntValue(wins, "total"));

        teamStatistics.setHomeDraws(getIntValue(draws, "home"));
        teamStatistics.setAwayDraws(getIntValue(draws, "away"));
        teamStatistics.setTotalDraws(getIntValue(draws, "total"));

        teamStatistics.setHomeLoses(getIntValue(loses, "home"));
        teamStatistics.setAwayLoses(getIntValue(loses, "away"));
        teamStatistics.setTotalLoses(getIntValue(loses, "total"));

        // Mapping goals
        Map<String, Object> goals = (Map<String, Object>) statistics.get("goals");
        Map<String, Object> goalsFor = (Map<String, Object>) goals.get("for");
        Map<String, Object> goalsAgainst = (Map<String, Object>) goals.get("against");

        Map<String, Object> totalGoalsFor = (Map<String, Object>) goalsFor.get("total");
        Map<String, Object> totalGoalsAgainst = (Map<String, Object>) goalsAgainst.get("total");

        teamStatistics.setGoalsForHome(getIntValue(totalGoalsFor, "home"));
        teamStatistics.setGoalsForAway(getIntValue(totalGoalsFor, "away"));
        teamStatistics.setGoalsForTotal(getIntValue(totalGoalsFor, "total"));

        teamStatistics.setGoalsAgainstHome(getIntValue(totalGoalsAgainst, "home"));
        teamStatistics.setGoalsAgainstAway(getIntValue(totalGoalsAgainst, "away"));
        teamStatistics.setGoalsAgainstTotal(getIntValue(totalGoalsAgainst, "total"));

        // Mapping clean sheets
        Map<String, Object> cleanSheet = (Map<String, Object>) statistics.get("clean_sheet");
        teamStatistics.setCleanSheetHome(getIntValue(cleanSheet, "home"));
        teamStatistics.setCleanSheetAway(getIntValue(cleanSheet, "away"));
        teamStatistics.setCleanSheetTotal(getIntValue(cleanSheet, "total"));

        // Mapping failed to score
        Map<String, Object> failedToScore = (Map<String, Object>) statistics.get("failed_to_score");
        teamStatistics.setFailedToScoreHome(getIntValue(failedToScore, "home"));
        teamStatistics.setFailedToScoreAway(getIntValue(failedToScore, "away"));
        teamStatistics.setFailedToScoreTotal(getIntValue(failedToScore, "total"));

        // Mapping cards
        Map<String, Object> cards = (Map<String, Object>) statistics.get("cards");
        Map<String, Object> yellowCards = (Map<String, Object>) cards.get("yellow");
        Map<String, Object> redCards = (Map<String, Object>) cards.get("red");

        teamStatistics.setYellowCards(getIntValue(yellowCards, "total"));
        teamStatistics.setRedCards(getIntValue(redCards, "total"));

        // Mapping the biggest streak, wins, loses, and goals
        Map<String, Object> biggest = (Map<String, Object>) statistics.get("biggest");

        Map<String, Object> streak = (Map<String, Object>) biggest.get("streak");
        teamStatistics.setBiggestWinStreak(getIntValue(streak, "wins"));
        teamStatistics.setBiggestDrawStreak(getIntValue(streak, "draws"));
        teamStatistics.setBiggestLoseStreak(getIntValue(streak, "loses"));

        Map<String, Object> biggestWins = (Map<String, Object>) biggest.get("wins");
        teamStatistics.setBiggestHomeWin(getStringValue(biggestWins, "home"));
        teamStatistics.setBiggestAwayWin(getStringValue(biggestWins, "away"));

        Map<String, Object> biggestLoses = (Map<String, Object>) biggest.get("loses");
        teamStatistics.setBiggestHomeLose(getStringValue(biggestLoses, "home"));
        teamStatistics.setBiggestAwayLose(getStringValue(biggestLoses, "away"));

        Map<String, Object> biggestGoals = (Map<String, Object>) biggest.get("goals");
        Map<String, Object> goalsForBiggest = (Map<String, Object>) biggestGoals.get("for");
        Map<String, Object> goalsAgainstBiggest = (Map<String, Object>) biggestGoals.get("against");

        teamStatistics.setBiggestGoalsForHome(getIntValue(goalsForBiggest, "home"));
        teamStatistics.setBiggestGoalsForAway(getIntValue(goalsForBiggest, "away"));

        teamStatistics.setBiggestGoalsAgainstHome(getIntValue(goalsAgainstBiggest, "home"));
        teamStatistics.setBiggestGoalsAgainstAway(getIntValue(goalsAgainstBiggest, "away"));

        return teamStatistics;
    }

    private String getStringValue(Map<String, Object> map, String key) {
        return map != null && map.containsKey(key) ? (String) map.get(key) : "";
    }

    private int getIntValue(Map<String, Object> map, String key) {
        return map != null && map.containsKey(key) ? ((Number) map.get(key)).intValue() : 0;
    }
}
