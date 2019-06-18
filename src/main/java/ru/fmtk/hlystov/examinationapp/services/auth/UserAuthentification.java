package ru.fmtk.hlystov.examinationapp.services.auth;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserAuthentification {
    @NotNull
    private final AppConfig appConfig;

    @Autowired
    public UserAuthentification(@Qualifier("appConfig") @NotNull AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @NotNull
    public Optional<User> getUser(UserCredential userCredential) {
        return Optional.ofNullable((userCredential != null) ? new User(userCredential) : null);
    }
}
