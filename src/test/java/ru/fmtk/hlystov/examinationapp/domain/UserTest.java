package ru.fmtk.hlystov.examinationapp.domain;

import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;
import ru.fmtk.hlystov.examinationapp.services.auth.UserCredential;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserTest {
    private final String firstName = "First";
    private final String secondName = "Second";
    private final String fullName = firstName + " " + secondName;

    @Nullable
    private User userAB;
    @Nullable
    private UserCredential userCredential;

    @Before
    public void initUserTest() {
        userCredential = new UserCredential(firstName, secondName);
        userAB = new User(userCredential);
    }

    @Test
    public void getFirstName() {
        if (userAB != null) {
            assertEquals(firstName, userAB.getFirstName());
        }
    }

    @Test
    public void getSecondName() {
        if (userAB != null) {
            assertEquals(secondName, userAB.getSecondName());
        }
    }

    @Test
    public void toStringTest() {
        if (userAB != null) {
            assertEquals(fullName, userAB.toString());
        }
    }

    @Test
    public void equalsTest() {
        assertEquals(userAB, new User(userCredential));
    }

    @Test
    public void notEqualsTest() {
        assertNotEquals(userAB, new User(new UserCredential(firstName, "#1234")));
    }
}