package br.com.erudio.restspringboot.services;

import br.com.erudio.restspringboot.controllers.PersonController;
import br.com.erudio.restspringboot.data.vo.v1.PersonVO;
import br.com.erudio.restspringboot.data.vo.v2.PersonVOV2;
import br.com.erudio.restspringboot.exceptions.ResourceNotFoundException;
import br.com.erudio.restspringboot.mapper.DozerMapper;
import br.com.erudio.restspringboot.mapper.custom.PersonMapper;
import br.com.erudio.restspringboot.model.Person;
import br.com.erudio.restspringboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;


    @Autowired
    PersonMapper personMapper;


    public List<PersonVO> findAll(){
        logger.info("Finding all people...");

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream().forEach(person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id){
        logger.info("Finding a person...");

        var entity = repository.findById(id)
                .orElseThrow(
                        () ->
                        new ResourceNotFoundException(
                                "No records found for this ID!"));

        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO createV1(PersonVO personVO) {
        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(personVO, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person with V2!");

        var entity = personMapper.convertVoTOEntity(person);

        return personMapper.convertEntityToVo(repository.save(entity));
    }

    public PersonVO update(PersonVO personVO) {

        logger.info("Updating one person!");

        var entity = repository.findById(personVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }

}
