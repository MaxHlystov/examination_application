package ru.fmtk.hlystov.examinationapp.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class User {
    private final String firstName;
    private final String secondName;


    public User(@NotNull String firstName, @NotNull String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    @NotNull
    public String getSecondName() {
        return secondName;
    }

    @NotNull
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
