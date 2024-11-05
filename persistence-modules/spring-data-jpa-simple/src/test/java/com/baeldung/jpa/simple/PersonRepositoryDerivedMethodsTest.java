package com.baeldung.jpa.simple;

import com.baeldung.jpa.simple.entity.Address;
import com.baeldung.jpa.simple.entity.Country;
import com.baeldung.jpa.simple.entity.Person;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import com.baeldung.jpa.simple.repository.PersonRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JpaApplication.class)
class PersonRepositoryDerivedMethodsTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Transactional
    void givenDbContainsPersonsWhenfindAllByAddressZipCodeThenReturnPersonsByZipCode() {

        Country spain = new Country("Spain", "SP");
        Country france = new Country("France", "FR");
        Country unitedStates = new Country("United States", "US");

        Address spanishAddress = new Address("28004", spain);
        Address frenchAddress = new Address("75001", france);
        Address usAddress = new Address("10001", unitedStates);

        Person auri = new Person("Auri", spanishAddress);
        Person yago = new Person("Yago", spanishAddress);
        Person clement = new Person("Clement", frenchAddress);
        Person georgios = new Person("Georgios", usAddress);

        personRepository.save(auri);
        personRepository.save(yago);
        personRepository.save(clement);
        personRepository.save(georgios);

        personRepository.flush();


        List<Person> allByAddressZipCode = personRepository.findAllByAddressZipCode("28004");
        List<Person> allByAddressCountryIsoCode = personRepository.findAllByAddressCountryIsoCode("SP");

        Assertions.assertEquals(2, allByAddressCountryIsoCode.size());
        Assertions.assertEquals(2, allByAddressZipCode.size());

    }
}
