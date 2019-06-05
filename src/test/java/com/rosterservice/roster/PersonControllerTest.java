package com.rosterservice.roster;

import static org.assertj.core.api.Assertions.assertThat;

import com.rosterservice.roster.controllers.PersonController;
import com.rosterservice.roster.models.Person;
import com.rosterservice.roster.services.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    public void testFindAllPeople() throws IOException {
        List<Person> people = new ArrayList<>();
        Person person1 = new Person("1", "AA", "BB");
        Person person2 = new Person("2", "AB", "DD");
        people.add(person1);
        people.add(person2);

        Mockito.when(personService.getPeople()).thenReturn(people);

        ResponseEntity<?> response = personController.getPeople();
        assertThat(((List<Person>) response.getBody()).size()).isEqualTo(2);
    }

    @Test
    public void testSearchPeopleByName() throws IOException {
        List<Person> people = new ArrayList<>();
        Person person1 = new Person("1", "Arya", "Stark");
        people.add(person1);

        Mockito.when(personService.searchPeopleByFirstOrLastName("")).thenReturn(people);

        ResponseEntity<?> response = personController.searchPeopleByName("");
        assertThat(((List<Person>) response.getBody()).size()).isEqualTo(1);

    }
}
