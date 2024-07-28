package com.example.FootyFocus.service;


import com.example.FootyFocus.entity.Person;
import com.example.FootyFocus.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepo personRepo;

    @Override
    public List<Person> getAllPerson(){
        return personRepo.findAll();
    }

    @Override
    public Person getPersonById(Long id){
        return personRepo.findById(id).orElse(null);
    }

    @Override
    public Person savePerson(Person person){
        return personRepo.save(person);
    }

    @Override
    public  void deletePerson(Long id){
        personRepo.deleteById(id);
    }
}
