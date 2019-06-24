package ru.fmtk.hlystov.examinationapp.services.auth;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;

import java.util.Optional;

@Service
public class UserAuthentication {
    @NotNull
    private final AppConfig appConfig;

    public UserAuthentication(@Qualifier("appConfig") @NotNull AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @NotNull
    public Optional<User> getUser(@Nullable UserCredential userCredential) {
        return Optional.ofNullable(userCredential).map(User::new);
    }
}
