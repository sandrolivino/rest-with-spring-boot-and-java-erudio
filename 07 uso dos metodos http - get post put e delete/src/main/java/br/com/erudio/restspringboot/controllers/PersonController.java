package br.com.erudio.restspringboot.controllers;

import br.com.erudio.restspringboot.model.Person;
import br.com.erudio.restspringboot.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices service = new PersonServices();

    @RequestMapping(
            value="/{id}",
            method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable("id") String id) throws Exception {
        return service.findById(id);
    }
}