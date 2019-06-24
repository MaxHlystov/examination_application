package ru.fmtk.hlystov.examinationapp.services.auth;

public class UserCredential {
    private final String firstName;
    private final String secondName;

    public UserCredential(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
