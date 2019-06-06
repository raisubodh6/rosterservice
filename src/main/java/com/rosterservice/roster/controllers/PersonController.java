package com.rosterservice.roster.controllers;

import com.rosterservice.roster.models.Person;
import com.rosterservice.roster.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/v1/people", method = RequestMethod.GET)
    public ResponseEntity<?> getPeople() {
        try {
            List<Person> people = personService.getPeople();
            return new ResponseEntity<>(people, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>("Runtime Exception encountered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/v1/people", method = RequestMethod.POST)
    public ResponseEntity<?> searchPeopleByName(@RequestBody Person person) {
        try {

            List<Person> people = personService.searchPeopleByFirstOrLastName(person);
            return new ResponseEntity<>(people, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>("Runtime Exception encountered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
