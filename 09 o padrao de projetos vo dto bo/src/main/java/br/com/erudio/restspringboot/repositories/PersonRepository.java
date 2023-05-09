package br.com.erudio.restspringboot.repositories;

import br.com.erudio.restspringboot.data.vo.v1.PersonVO;
import br.com.erudio.restspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonVO, Long> {}
