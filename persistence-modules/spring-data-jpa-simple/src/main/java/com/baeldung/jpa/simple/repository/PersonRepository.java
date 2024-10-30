package com.baeldung.jpa.simple.repository;

import com.baeldung.jpa.simple.entity.Cart;
import com.baeldung.jpa.simple.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findAllByAddressZipCode(String zipCode);

//    List<Person> findAllByAddressCountry(String zipCode);
//
//    List<Person> findAllByAddress_Country(String zipCode);
//
//    List<Person> findAllByAddressCountryIsoCode(String zipCode);
//
//    List<Person> findAllByAddress_CountryIsoCode(String zipCode);
//
//    List<Person> findAllByAddress_Country_IsoCode(String zipCode);
//
//    List<Person> findAllByAddress_CountryInvalid(String zipCode);
//
//    List<Person> findAllBy_(String zipCode);
}
