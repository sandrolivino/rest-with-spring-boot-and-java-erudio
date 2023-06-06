package br.com.erudio.restspringboot.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "first_name", "last_name", "address", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    // Alterado de id para key, pois o RepresentionModel já possui um id
    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;
    private String address;
    private String gender;

    public PersonVO() {
    }

    public PersonVO(Long key, String firstName, String lastName, String address, String gender) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonVO personVO = (PersonVO) o;
        return key.equals(personVO.key) && firstName.equals(personVO.firstName) && lastName.equals(personVO.lastName) && address.equals(personVO.address) && gender.equals(personVO.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, firstName, lastName, address, gender);
    }
}
