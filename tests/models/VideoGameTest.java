package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VideoGameTest {

    VideoGame videoGame;

    @BeforeEach
    void setUp() {
        videoGame = new VideoGame("Valorant", 9, 0);
    }

    @Test
    void setName() {
        videoGame.setName("Cs");
        assertEquals("Cs", videoGame.getName());
    }

    @Test
    void setNameInvalid() {
        assertThrows(IllegalArgumentException.class, ()->videoGame.setName(""));
    }

    @Test
    void setAgeRating() {
        videoGame.setAgeRating(2);
        assertEquals(2, videoGame.getAgeRating());
    }
}