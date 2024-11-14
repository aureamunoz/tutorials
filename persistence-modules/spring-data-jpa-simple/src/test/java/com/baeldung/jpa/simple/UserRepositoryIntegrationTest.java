package com.baeldung.jpa.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.baeldung.jpa.simple.entity.User;
import com.baeldung.jpa.simple.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpaApplication.class)
class UserRepositoryIntegrationTest {

    private static final String USER_NAME_ADAM = "Adam";
    private static final String USER_NAME_EVE = "Eve";
    private static final ZonedDateTime BIRTHDATE = ZonedDateTime.now();

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {

        User user1 = new User(USER_NAME_ADAM, 25, BIRTHDATE, true);
        User user2 = new User(USER_NAME_ADAM, 20, BIRTHDATE, false);
        User user3 = new User(USER_NAME_EVE, 20, BIRTHDATE, true);
        User user4 = new User(null, 30, BIRTHDATE, false);
        User user5 = new User("Tim", 38, BIRTHDATE, false);
        User user6 = new User("Timothee", 19, BIRTHDATE, false);

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5,user6));
    }

    @AfterEach
    public void tearDown() {

        userRepository.deleteAll();
    }

    @Test
    void whenFindByName_thenReturnsCorrectResult() {

        assertEquals(2, userRepository.findByName(USER_NAME_ADAM)
            .size());
    }

    @Test
    void whenFindByNameIsNull_thenReturnsCorrectResult() {

        assertEquals(1, userRepository.findByNameIsNull()
            .size());
        assertEquals(5, userRepository.findByNameIsNotNull()
                .size());
    }

    @Test
    void whenFindByNameNot_thenReturnsCorrectResult() {

        List<User> byNameNot = userRepository.findByNameNot(USER_NAME_ADAM);
        assertEquals(USER_NAME_EVE, byNameNot
            .get(0)
            .getName());
    }

    @Test
    void whenFindByNameStartingWith_thenReturnsCorrectResult() {

        assertEquals(2, userRepository.findByNameStartingWith("A")
            .size());
    }

    @Test
    void whenFindByNameEndingWith_thenReturnsCorrectResult() {

        assertEquals(1, userRepository.findByNameEndingWith("e")
            .size());
    }

    @Test
    void whenByNameContaining_thenReturnsCorrectResult() {

        assertEquals(1, userRepository.findByNameContaining("v")
            .size());
    }

    @Test
    void whenByNameLike_thenReturnsCorrectResult() {

        assertEquals(2, userRepository.findByNameEndingWith("m")
            .size());
    }

    @Test
    void whenByAgeLessThan_thenReturnsCorrectResult() {

        assertEquals(2, userRepository.findByAgeLessThan(25)
            .size());
    }

    @Test
    void whenByAgeLessThanEqual_thenReturnsCorrectResult() {

        assertEquals(3, userRepository.findByAgeLessThanEqual(25)
            .size());
    }

    @Test
    void whenByAgeGreaterThan_thenReturnsCorrectResult() {

        List<User> byAgeGreaterThan = userRepository.findByAgeGreaterThan(25);
        assertEquals(1, byAgeGreaterThan
            .size());
    }

    @Test
    void whenByAgeGreaterThanEqual_thenReturnsCorrectResult() {

        assertEquals(2, userRepository.findByAgeGreaterThanEqual(25)
            .size());
    }

    @Test
    void whenByAgeBetween_thenReturnsCorrectResult() {

        assertEquals(4, userRepository.findByAgeBetween(20, 30)
            .size());
    }

    @Test
    void whenByBirthDateAfter_thenReturnsCorrectResult() {

        final ZonedDateTime yesterday = BIRTHDATE.minusDays(1);
        assertEquals(4, userRepository.findByBirthDateAfter(yesterday)
            .size());
    }

    @Test
    void whenByBirthDateBefore_thenReturnsCorrectResult() {

        final ZonedDateTime yesterday = BIRTHDATE.minusDays(1);
        assertEquals(0, userRepository.findByBirthDateBefore(yesterday)
            .size());
    }

    @Test
    void whenByActiveTrue_thenReturnsCorrectResult() {

        assertEquals(2, userRepository.findByActiveTrue()
            .size());
    }

    @Test
    void whenByActiveFalse_thenReturnsCorrectResult() {

        assertEquals(2, userRepository.findByActiveFalse()
            .size());
    }

    @Test
    void whenByAgeIn_thenReturnsCorrectResult() {

        final List<Integer> ages = Arrays.asList(20, 25);
        assertEquals(3, userRepository.findByAgeIn(ages)
            .size());
    }

    @Test
    void whenByNameOrAge() {

        assertEquals(3, userRepository.findByNameOrAge(USER_NAME_ADAM, 20)
            .size());
    }

    @Test
    void whenByNameOrAgeAndActive() {

        assertEquals(2, userRepository.findByNameOrAgeAndActive(USER_NAME_ADAM, 20, false)
            .size());
    }

    @Test
    @Transactional
    void whenByNameAndAgeOrActive() {

        assertEquals(1, userRepository.findAllByNameAndAgeOrActive("Adam", 20, true)
                .size());
    }

    @Test
    @Transactional
    void whenByNameOrAgeOrActive() {

        assertEquals(3, userRepository.findAllByNameOrAgeOrActive("Adam", 20, true)
                .size());
    }

    @Test
    void whenByNameOrderByName() {

        assertEquals(2, userRepository.findByNameOrderByName(USER_NAME_ADAM)
            .size());
    }

    @Test
    @Transactional
    void whenFindByNameLikePatternThenReturnsCorrectResult() {

        assertEquals(2, userRepository.findByNameLike("%Tim%")
                .size());
    }

}