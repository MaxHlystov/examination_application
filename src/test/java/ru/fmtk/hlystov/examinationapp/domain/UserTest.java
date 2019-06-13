package ru.fmtk.hlystov.examinationapp.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.lang.Nullable;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Nullable
    private User userAB;

    @Before
    public void initUserTest() {
        userAB = new User("a", "b");
    }

    @Test
    public void getFirstName() {
        if (userAB != null) {
            assertEquals("a", userAB.getFirstName());
        }
    }

    @Test
    public void getSecondName() {
        if (userAB != null) {
            assertEquals("b", userAB.getSecondName());
        }
    }

    @Test
    public void toStringTest() {
        if (userAB != null) {
            assertEquals("a b", userAB.toString());
        }
    }

    @Test
    public void equalsTest() {
        assertEquals(userAB, new User("a", "b"));
    }

}