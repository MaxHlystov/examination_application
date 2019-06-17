package ru.fmtk.hlystov.examinationapp.services.auth;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ConsoleUserAuth implements UserAuthentification {
    @NotNull
    private final AppConfig appConfig;
    @NotNull
    private final Scanner sc;
    @NotNull
    private final PrintStream out;

    @Autowired
    public ConsoleUserAuth(@Qualifier("appConfig") @NotNull AppConfig appConfig) {
        this(appConfig, System.in, System.out);
    }

    public ConsoleUserAuth(@NotNull AppConfig appConfig, @NotNull InputStream in, @NotNull PrintStream out) {
        this.appConfig = appConfig;
        this.sc = new Scanner(in);
        this.out = out;
    }

    @Override
    @NotNull
    public Optional<User> getUser() {
        User user = null;
        try {
            out.println(appConfig.getMessage("authentification.whats-first-name", null));
            String firstName = sc.nextLine();
            out.println(appConfig.getMessage("authentification.whats-second-name", null));
            String secondName = sc.nextLine();
            user = new User(firstName, secondName);
        } catch (NoSuchElementException | IllegalStateException ignored) {
        }
        return Optional.ofNullable(user);
    }
}
