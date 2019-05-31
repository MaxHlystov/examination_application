package ru.fmtk.hlystov.hw_examination_app.services.auth;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.Main;
import ru.fmtk.hlystov.hw_examination_app.domain.IUser;
import ru.fmtk.hlystov.hw_examination_app.domain.User;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleUserAuth implements IUserAuthentification {
    @NonNull
    private final Scanner sc;
    @NonNull
    private final PrintStream out;

    public ConsoleUserAuth() {
        this(System.in, System.out);
    }

    public ConsoleUserAuth(@NonNull InputStream in, @NonNull PrintStream out) {
        sc = new Scanner(in);
        this.out = out;
    }

    @Override
    @Nullable
    public IUser getUser() {
        IUser user = null;
        try {
            out.println(Main.getAppContext().getString("whatsFirstName"));
            String firstName = sc.next();
            out.println(Main.getAppContext().getString("whatsSecondName"));
            String secondName = sc.next();
            user = new User(firstName, secondName);
        } catch (NoSuchElementException | IllegalStateException ignoredToNull) {
        }
        return user;
    }
}
