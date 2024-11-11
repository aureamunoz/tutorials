package com.baeldung.jpa.simple.repository;

import com.baeldung.jpa.simple.entity.Cart;
import com.baeldung.jpa.simple.entity.Person;
import com.baeldung.jpa.simple.entity.User;
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
//select p1_0.id,p1_0.address_id,p1_0.name from Person p1_0 left join Address a1_0 on a1_0.id=p1_0.address_id left join Country c1_0 on c1_0.id=a1_0.country_id where c1_0.isoCode=?
    List<Person> findAllByAddressCountryIsoCode(String isoCode);
//
    List<Person> findAllByAddress_CountryIsoCode(String zipCode);
//
//    List<Person> findAllByAddress_Country_IsoCode(String zipCode);
//
//    List<Person> findAllByAddress_CountryInvalid(String zipCode);
//
//    List<Person> findAllBy_(String zipCode);


    //----------nested fields--------------------------
    //    List<User> findAllByAddressCountry(String country);
    //
    //    List<User> findAllByAddress_Country(String country);
    //
    //    List<User> findAllByAddress_CountryIsoCode(String isoCode);
    //
    //    List<User> findAllByAddress_Country_IsoCode(String isoCode);

    //------------------------------------------------------------

}
