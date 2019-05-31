package ru.fmtk.hlystov.hw_examination_app;

import org.junit.Before;
import org.junit.Test;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.domain.User;

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
        assertEquals("a", userAB.getFirstName());
    }

    @Test
    public void getSecondName() {
        assertEquals("b", userAB.getFirstName());
    }

    @Test
    public void toString1() {
        assertEquals("a b", userAB.toString());
    }

    @Test
    public void equals1() {
        assertEquals(userAB, new User("a", "b"));
    }
}