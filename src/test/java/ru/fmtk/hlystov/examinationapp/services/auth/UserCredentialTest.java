package ru.fmtk.hlystov.examinationapp.services.auth;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserCredentialTest {
    private final String firstName = "First";
    private final String secondName = "Second";

    @Test
    public void getFirstName() {
        UserCredential userCredential = new UserCredential(firstName, secondName);
        assertEquals(firstName, userCredential.getFirstName());
    }

    @Test
    public void getSecondName() {
        UserCredential userCredential = new UserCredential(firstName, secondName);
        assertEquals(secondName, userCredential.getSecondName());
    }
}