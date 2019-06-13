package ru.fmtk.hlystov.examinationapp.services.auth;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import ru.fmtk.hlystov.examinationapp.Application;
import ru.fmtk.hlystov.examinationapp.TestConsole;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsoleUserAuthTest {
    private TestConsole testConsole;
    private AppConfig appConfig;
    private ConsoleUserAuth userAuth;

    @Before
    public void initTests() throws IOException {
        testConsole = new TestConsole();
        setAppConfig(new Locale("en", "En"));
    }

    private void setAppConfig(@NotNull Locale locale) {
        Application app = new Application();
        appConfig = new AppConfig(locale, "simple-exam-5-questions.csv", app.messageSource());
        userAuth = new ConsoleUserAuth(appConfig,
                testConsole.getInputStream(), testConsole.getPrintStream());
    }

    @Test
    public void getUserFirstSecond() throws IOException {
        String firstName = "First Name 1234";
        String secondName = "Second name %$#";
        testConsole.printlnToStdin(firstName);
        testConsole.printlnToStdin(secondName);
        Optional<User> otpUser = userAuth.getUser();
        assertTrue(otpUser.isPresent());
        assertEquals(firstName, otpUser.get().getFirstName());
        assertEquals(secondName, otpUser.get().getSecondName());
    }

    @Test
    public void getUserOnlyFirst() throws IOException {
        String firstName = "First Name 1234";
        testConsole.printlnToStdin(firstName);
        testConsole.printlnToStdin("");
        Optional<User> otpUser = userAuth.getUser();
        assertTrue(otpUser.isPresent());
        assertEquals(firstName, otpUser.get().getFirstName());
        assertEquals("", otpUser.get().getSecondName());
    }

    @Test
    public void getUserEnScreenMatching() throws IOException {
        String firstName = "First Name 1234";
        String secondName = "Second name %$#";
        testConsole.printlnToStdin(firstName);
        testConsole.printlnToStdin(secondName);
        testConsole.clearScreen();
        userAuth.getUser();
        Scanner sc = testConsole.getCurrentScreenScanner();
        assertEquals(appConfig.getMessage(
                "authentification.whats-first-name", null),
                sc.nextLine());
        assertEquals(appConfig.getMessage(
                "authentification.whats-second-name", null),
                sc.nextLine());
    }

    @Test
    public void getUserRuScreenMatching() throws IOException {
        setAppConfig(new Locale("ru", "Ru"));
        String firstName = "First Name 1234";
        String secondName = "Second name %$#";
        testConsole.printlnToStdin(firstName);
        testConsole.printlnToStdin(secondName);
        testConsole.clearScreen();
        userAuth.getUser();
        Scanner sc = testConsole.getCurrentScreenScanner();
        assertEquals(appConfig.getMessage(
                "authentification.whats-first-name", null),
                sc.nextLine());
        assertEquals(appConfig.getMessage(
                "authentification.whats-second-name", null),
                sc.nextLine());
    }
}