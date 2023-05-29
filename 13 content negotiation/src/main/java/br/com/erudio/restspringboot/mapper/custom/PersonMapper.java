package br.com.erudio.restspringboot.mapper.custom;

import br.com.erudio.restspringboot.data.vo.v2.PersonVOV2;
import br.com.erudio.restspringboot.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVo(Person person) {
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date());
        vo.setFirst_name(person.getFirstName());
        vo.setLast_name(person.getLastName());
        vo.setGender(person.getGender());

        return vo;
    }


    public Person convertVoTOEntity(PersonVOV2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        // entity.setBirthDay = (person.getBirthDay());
        entity.setFirstName(person.getFirst_name());
        entity.setLastName(person.getLast_name());
        entity.setGender(person.getGender());
        return entity;
    }

}
