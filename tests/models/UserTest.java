package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User("Illia_Admin", LocalDate.of(2003, 07, 30), 228541111);
    }

    @Test
    void setName() {
        user.setName("Nicks_");
        assertEquals("Nicks_", user.getName());
    }

    @Test
    void setNameInvalid() {
        assertThrows(IllegalArgumentException.class, ()->user.setName(""));
    }


    @Test
    void setBirthday() {
        user.setBirthday(LocalDate.of(2000,02,28));
        assertEquals(LocalDate.of(2000,02,28),user.getBirthday());
    }

    @Test
    void setInvalidBirthdayFuture() {
        assertThrows(IllegalArgumentException.class, ()->
                user.setBirthday(LocalDate.of(2030,02,02)));
    }

    @Test
    void setCreditCard() {
        user.setCreditCard(200541111);
        assertEquals(200541111, user.getCreditCard());
    }

    @Test
    void getAge() {
        assertEquals(17, user.getAge());
    }
}