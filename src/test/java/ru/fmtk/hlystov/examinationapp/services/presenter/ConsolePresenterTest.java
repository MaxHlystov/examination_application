package ru.fmtk.hlystov.examinationapp.services.presenter;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import ru.fmtk.hlystov.examinationapp.Application;
import ru.fmtk.hlystov.examinationapp.TestConsole;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.NumericAnswer;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.NumericQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;
import ru.fmtk.hlystov.examinationapp.services.auth.UserAuthentification;
import ru.fmtk.hlystov.examinationapp.services.converter.StringsToAnswerConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsolePresenterTest {
    private TestConsole testConsole;
    private User user;
    private ConsolePresenter consolePresenter;

    @Before
    public void initTest() throws IOException {
        testConsole = new TestConsole();
        Application app = new Application();
        AppConfig appConfig = new AppConfig(new Locale("en", "En"),
                "simple-exam-5-questions.csv",
                app.messageSource());
        user = new User("a", "b");
        UserAuthStub userAuth = new UserAuthStub(user);
        StringsToAnswerConverter answerConverter = new StringsToAnswerConverter();
        consolePresenter = new ConsolePresenter(appConfig, userAuth, answerConverter,
                testConsole.getInputStream(), testConsole.getPrintStream());
    }

    @Test
    public void showMessage() {
        String messageEn = "Test message 1234!";
        consolePresenter.showMessage(messageEn);
        Scanner sc = testConsole.getCurrentScreenScanner();
        assertEquals(messageEn, sc.nextLine());
    }

    @Test
    public void readString() throws IOException {
        String messageEn = "Test message 1234!";
        testConsole.printlnToStdin(messageEn);
        String newString = consolePresenter.readString();
        assertEquals(messageEn, newString);
    }

    @Test
    public void getUserNotNull() {
        User gotUser = consolePresenter.getUser();
        assertEquals(user, gotUser);
    }

    @Test
    public void askNumericQuestionWithRightAnswer() throws IOException {
        double rightNumber = 5.333;
        String title = "Numeric title 1";
        List<String> options = new ArrayList<>();
        NumericAnswer rightAnswer = new NumericAnswer(rightNumber);
        int number = 0;
        Question question = new NumericQuestion(title, options, rightAnswer);
        testConsole.printlnToStdin(Double.toString(rightNumber));
        Answer newAnswer = consolePresenter.askQuestion(number, question);
        assertTrue(rightAnswer.isEquals(newAnswer));
    }

    @Test
    public void askNumericQuestionWithWrongAnswer() throws IOException {
        double rightNumber = 5.333;
        String title = "Numeric title 1";
        List<String> options = new ArrayList<>();
        NumericAnswer rightAnswer = new NumericAnswer(rightNumber);
        int number = 0;
        Question question = new NumericQuestion(title, options, rightAnswer);
        testConsole.printlnToStdin(Double.toString(rightNumber + 100.0));
        Answer newAnswer = consolePresenter.askQuestion(number, question);
        assertFalse(rightAnswer.isEquals(newAnswer));
    }

    public class UserAuthStub implements UserAuthentification {
        @NotNull
        final
        User user;

        public UserAuthStub(@NotNull User user) {
            this.user = user;
        }

        @Override
        @NotNull
        public User getUser() {
            return user;
        }
    }
}
