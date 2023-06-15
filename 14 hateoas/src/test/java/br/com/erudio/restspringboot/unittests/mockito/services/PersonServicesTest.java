package br.com.erudio.restspringboot.unittests.mockito.services;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import br.com.erudio.restspringboot.model.Person;
import br.com.erudio.restspringboot.repositories.PersonRepository;
import br.com.erudio.restspringboot.services.PersonServices;
import br.com.erudio.restspringboot.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


// Definir o ciclo de vida
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {
    MockPerson input;

    // Injetar mocks: com essa annotation, apesar de estarmos acessando o PersonServices real, o acesso ao repository será apenas um mock.
    @InjectMocks
    private PersonServices services;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
    }

    // 1 - Primeiro mock
    @Test
    void findById() {
        // Já iniciamos o mock com número 1
        Person person = input.mockEntity(1);

        // Temos que setar um id, pois o mock não tem
        person.setId(1L);

        // Adicionar o: import static org.mockito.Mockito.when;
        // Criar um mock para quando o repository for chamado, retornar um mock
        // Para isso, mockar o repository nessa classe:
        // @Mock
        // PersonRepository repository;
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        // Observar que o resultado aqui será o "person" que nós mockamos aqui nesse método findById

        var result = services.findById(1L);

        // Verificações

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        // Usar o System.out para mostrar o resultado. Copiar e colar no assertTrue
        // System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        // Recuperar esses valores de MockPerson.java
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        // Female, pois o id 1 é impar
        assertEquals("Female", result.getGender());
    }

    @Test
    void createV1() {
    }

    @Test
    void createV2() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}