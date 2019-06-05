package com.rosterservice.roster.controllers;

import com.rosterservice.roster.models.Person;
import com.rosterservice.roster.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/v1/people/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getPeople(@PathVariable String name) {
        try {

            List<Person> people = personService.searchPeopleByFirstOrLastName(name);
            return new ResponseEntity<>(people, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>("Runtime Exception encountered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
