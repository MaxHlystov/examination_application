package ru.fmtk.hlystov.examinationapp.domain;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.services.auth.UserCredential;

import java.util.Objects;

public class User {
    private final String firstName;
    private final String secondName;


    public User(@NotNull UserCredential userCredential) {
        this.firstName = userCredential.getFirstName();
        this.secondName = userCredential.getSecondName();
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
