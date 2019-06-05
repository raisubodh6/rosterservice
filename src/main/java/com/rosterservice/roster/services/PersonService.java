package com.rosterservice.roster.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosterservice.roster.models.Person;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final static String PEOPLE_DATA_PATH = "./src/main/resources/people.json";

    /**
     *
     * @return
     * @throws IOException
     */
    public List<Person> getPeople() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String data = new String(Files.readAllBytes(Paths.get(PEOPLE_DATA_PATH)), Charset.defaultCharset());
        List<Person> people = mapper.readValue(data, new TypeReference<List<Person>>(){ });

        return people;
    }

    /**
     *
     * @param name
     * @return
     * @throws IOException
     */
    public List<Person> searchPeopleByFirstOrLastName(String name) throws IOException {
        if (null == name) {
            throw new IllegalArgumentException("Please provide first or last name");
        }

        ObjectMapper mapper = new ObjectMapper();
        String data = new String(Files.readAllBytes(Paths.get(PEOPLE_DATA_PATH)), Charset.defaultCharset());
        List<Person> people = mapper.readValue(data, new TypeReference<List<Person>>(){ });

        List<Person> filteredPeople =
                people.stream()
                        .filter(person -> person.getLastname().equals(name) || person.getFirstname().equals(name))
                        .collect(Collectors.toList());

        if (StringUtils.isEmpty(filteredPeople)) {
            throw new FileNotFoundException("Not Found");
        }

        return filteredPeople;
    }
}
