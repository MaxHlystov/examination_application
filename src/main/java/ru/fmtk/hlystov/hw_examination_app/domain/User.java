package ru.fmtk.hlystov.hw_examination_app.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class User implements IUser {
    private final String firstName;
    private final String secondName;


    public User(@NonNull String firstName, @NonNull String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getSecondName() {
        return secondName;
    }

    @NonNull
    @Override
    public String toString() {
        return firstName + " " + secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return firstName.equals(user.firstName) &&
                secondName.equals(user.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }
}
