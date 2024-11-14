package com.baeldung.jpa.simple;

import com.baeldung.jpa.simple.entity.Address;
import com.baeldung.jpa.simple.entity.Country;
import com.baeldung.jpa.simple.entity.Person;
import com.baeldung.jpa.simple.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import com.baeldung.jpa.simple.repository.PersonRepository;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JpaApplication.class)
class PersonRepositoryDerivedMethodsTest {

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {

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

        personRepository.saveAll(Arrays.asList(auri,yago,clement,georgios));
    }

    @AfterEach
    public void tearDown() {

        personRepository.deleteAll();
    }

    @Test
    @Transactional
    void givenDbContainsPersonsWhenfindAllByAddressZipCodeThenReturnPersonsByZipCode() {



        List<Person> allByAddressZipCode = personRepository.findAllByAddressZipCode("28004");
        List<Person> allByAddressCountryIsoCode = personRepository.findAllByAddressCountryIsoCode("SP");
        long spanishCustomers = personRepository.countPersonByAddressCountryName("Spain");
        Assertions.assertEquals(2, spanishCustomers);

        Assertions.assertEquals(2, allByAddressCountryIsoCode.size());
        Assertions.assertEquals(2, allByAddressZipCode.size());

    }

    @Test
    @Transactional
    void findByAddressZipCode() {

        assertEquals(2, personRepository.findAllByAddressZipCode("28004")
                .size());
    }

    @Test
    @Transactional
    void findByAddressCountryIsoCode() {
        //select p1_0.id,p1_0.address_id,p1_0.name from Person p1_0 left join Address a1_0 on a1_0.id=p1_0.address_id left join Country c1_0 on c1_0.id=a1_0.country_id where c1_0.isoCode=?
        assertEquals(2, personRepository.findAllByAddressCountryIsoCode("SP")
                .size());
        assertEquals(2, personRepository.findAllByAddress_CountryIsoCode("SP")
                .size());
    }

    @Test
    @Transactional
    void findByAddressCountry() {

//        assertEquals(2, personRepository.findAllByAddressCountry("Spain")
//                .size());
//        assertEquals(2, personRepository.findAllByAddress_Country("Spain")
//                .size());
    }

    @Test
    @Transactional
    void shouldCountSpanishCustomers() {

        long spanishCustomers = personRepository.countPersonByAddressCountryName("Spain");
        Assertions.assertEquals(2, spanishCustomers);

    }
}
