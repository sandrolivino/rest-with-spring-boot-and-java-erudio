package br.com.erudio.restspringboot.controllers;

import br.com.erudio.restspringboot.data.vo.v1.PersonVO;
import br.com.erudio.restspringboot.data.vo.v2.PersonVOV2;
import br.com.erudio.restspringboot.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    private PersonServices service = new PersonServices();

    @GetMapping(value = "/v1/{id}"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/all/v1"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PostMapping( value = "/v1"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO create(@RequestBody PersonVO personVO) {
        return service.createV1(personVO);
    }

    @PostMapping(
            value = "/v2"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2) {
        return service.createV2(personVOV2);
    }


    @PutMapping(value = "/v1"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO update(@RequestBody PersonVO personVO) {
        return service.update(personVO);
    }

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}