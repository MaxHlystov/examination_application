package ru.fmtk.hlystov.examinationapp.services.auth;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.User;

import java.util.Optional;

public interface UserAuthentification {
    @NotNull
    Optional<User> getUser();
}
