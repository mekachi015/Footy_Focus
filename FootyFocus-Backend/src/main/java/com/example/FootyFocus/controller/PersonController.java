package com.example.FootyFocus.controller;


import com.example.FootyFocus.entity.Person;
import com.example.FootyFocus.service.APIServices.ApiService;
import com.example.FootyFocus.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v4/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private PersonService personService;

    //gets all the players in player watchlist
    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id){
        return  apiService.fetchPersonFromApi(id);
    }

    @PostMapping
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @DeleteMapping("/{id}")
    public  void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }

    @GetMapping("/search")
    public Person searchPlayerByName(@RequestParam String name) {
        return apiService.fetchPersonByName(name);
    }


}
