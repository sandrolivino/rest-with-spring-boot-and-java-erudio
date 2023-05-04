package br.com.erudio.restspringboot.services;

import br.com.erudio.restspringboot.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

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
}
