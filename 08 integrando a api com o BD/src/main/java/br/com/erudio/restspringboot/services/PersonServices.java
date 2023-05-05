package br.com.erudio.restspringboot.services;

import br.com.erudio.restspringboot.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        logger.info("Finding all people...");

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person findById(String id){
        logger.info("Finding a person...");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Sandro");
        person.setLastName("Siqueira");
        person.setAddress("Ceilândia - Brasília - DF");
        person.setGender("Male");

        return person;
    }

    public Person create(Person person) {

        logger.info("Creating one person!");

        return person;
    }

    public Person update(Person person) {

        logger.info("Updating one person!");

        return person;
    }

    public void delete(String id) {

        logger.info("Deleting one person!");
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName(i + " - Firstname ");
        person.setLastName("Lastname");
        person.setAddress("Some address in Brazil ");
        person.setGender("Male");

        return person;
    }

}
