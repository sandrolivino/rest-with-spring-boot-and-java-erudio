package br.com.erudio.restspringboot.services;

import br.com.erudio.restspringboot.data.vo.v1.PersonVO;
import br.com.erudio.restspringboot.exceptions.ResourceNotFoundException;
import br.com.erudio.restspringboot.mapper.DozerMapper;
import br.com.erudio.restspringboot.model.Person;
import br.com.erudio.restspringboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll(){
        logger.info("Finding all people...");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){
        logger.info("Finding a person...");

        var entity = repository.findById(id)
                .orElseThrow(
                        () ->
                        new ResourceNotFoundException(
                                "No records found for this ID!"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO personVO) {

        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(personVO, Person.class);

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVO update(PersonVO personVO) {

        logger.info("Updating one person!");

        var entity = repository.findById(personVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
