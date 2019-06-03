package ru.fmtk.hlystov.examinationapp.services.auth;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.fmtk.hlystov.examinationapp.Main;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.UserImpl;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleUserAuth implements UserAuthentification {
    @NotNull
    private final Scanner sc;
    @NotNull
    private final PrintStream out;

    public ConsoleUserAuth() {
        this(System.in, System.out);
    }

    public ConsoleUserAuth(@NotNull InputStream in, @NotNull PrintStream out) {
        sc = new Scanner(in);
        this.out = out;
    }

    @Override
    @Nullable
    public User getUser() {
        User user = null;
        try {
            out.println(Main.getLocalizationService().getString("authentification.whats-first-name"));
            String firstName = sc.next();
            out.println(Main.getLocalizationService().getString("authentification.whats-second-name"));
            String secondName = sc.next();
            user = new UserImpl(firstName, secondName);
        } catch (NoSuchElementException | IllegalStateException ignoredToNull) {
        }
        return user;
    }
}
