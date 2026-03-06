package org.example.footballevents;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LeagueTest {

    @Test
    void getId() {
        assertEquals(235, League.RUSSIAN_PREMIER_LEAGUE.getId());
        assertEquals(236, League.RUSSIAN_FIRST_LEAGUE.getId());
        assertEquals(78, League.BUNDESLIGA.getId());
    }

    @Test
    void getIds() {
        assertTrue(League.getIds().contains(235));
    }

    @Test
    void values() {
        assertTrue(Arrays.asList(League.values()).contains(League.RUSSIAN_PREMIER_LEAGUE));
    }

    @Test
    void valueOf() {
        assertEquals(League.valueOf("RUSSIAN_PREMIER_LEAGUE").getId(),235);
    }
}