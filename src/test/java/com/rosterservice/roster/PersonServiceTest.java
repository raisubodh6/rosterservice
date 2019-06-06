package com.rosterservice.roster;

import static org.assertj.core.api.Assertions.assertThat;

import com.rosterservice.roster.models.Person;
import com.rosterservice.roster.services.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Test
    public void testFindPeopleReturnsFullRoster() throws IOException {
        List<Person> people = personService.getPeople();
        assertThat(people.size()).isEqualTo(4);
    }

    @Test
    public void testSearchPeopleReturnsPersonByName() throws IOException {
        Person person = new Person("1", "Arya", "");
        List<Person> people = personService.searchPeopleByFirstOrLastName(person);

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getFirstname()).isEqualTo(person.getFirstname());
    }
}
