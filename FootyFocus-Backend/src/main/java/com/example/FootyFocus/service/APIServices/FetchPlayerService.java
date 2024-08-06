package com.example.FootyFocus.service.APIServices;

import com.example.FootyFocus.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FetchPlayerService {

    private ApiService apiService;


    public PlayerApiService(ApiService apiService){
        this.apiService = apiService;
        return;
    }

    public Person fetchPersonByFullName(String firstName, String lastName){
        String fullName = firstName + ' ' + lastName;
        return fetchPersonByName(fullName);
    }

    private Person fetchPersonByName(String fullname){
        try{
            Long id = searchPersonByFullName(fullname);
            return apiService.fetchPersonFromApi(id);
        } catch ( Exception e){
            throw new RuntimeException("Error fetching player information" + e.getMessage())
        }
    }

    private Long searchPersonByFullName(String fullName){
        try {
            List<Map<String, Object>> players = apiService.fetchAllPlayers();
            for (Map<String, Object> player : players) {
                if (fullName.equalsIgnoreCase((String) player.get("name"))) {
                    return ((Number) player.get("id")).longValue();
                }
            }
            throw new RuntimeException("No player found with name: " + fullName);
        } catch (Exception e) {
            throw new RuntimeException("Error searching for player: " + e.getMessage());
        }
    }

}


