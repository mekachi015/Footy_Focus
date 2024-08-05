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

    @GetMapping("/fetch/{id}")
    public Person fetchAndSavePerson(@PathVariable Long id) {
        Person person = apiService.fetchPersonFromApi(id);
        return personService.savePerson(person);
    }

    @PostMapping("/save/{id}")
    public Person savePersonById(@PathVariable Long id){
        Person person = apiService.fetchPersonFromApi(id);
        return personService.savePerson(person);
    }
//
//    // New endpoint to search for a player by name
//    @GetMapping("/search")
//    public Person searchPersonByName(@RequestParam String name) {
//        return apiService.searchPersonByName(name);
//    }

//    // New endpoint to search for a player by name and return the player's ID
//    @GetMapping("/search")
//    public Long searchPersonByNameReturnId(@RequestParam String name) {
//        return apiService.searchPersonByNameReturnId(name);
//    }

//    @GetMapping("/search")
//    public Person searchPersonByName(@RequestParam String name) {
//        return apiService.fetchPersonByName(name);
//    }


}
