package ru.fmtk.hlystov.examinationapp.services.presenter;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import ru.fmtk.hlystov.examinationapp.Application;
import ru.fmtk.hlystov.examinationapp.TestConsole;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.NumericAnswer;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.NumericQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;
import ru.fmtk.hlystov.examinationapp.services.auth.UserCredential;
import ru.fmtk.hlystov.examinationapp.services.converter.StringsToAnswerConverter;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

@TestComponent
public class ConsolePresenterTest {
    private TestConsole testConsole;
    private UserCredential userCredential;
    private User user;
    private ConsolePresenter consolePresenter;

    @Autowired
    public void setTestConsole(TestConsole testConsole) {
        this.testConsole = testConsole;
    }

    @Before
    public void initTest() throws IOException {
        testConsole = new TestConsole();
        AppConfig appConfig = new AppConfig(new Locale("en", "En"),
                Application.messageSource());
        appConfig.setBaseCSVResourceName("simple-exam-5-questions.csv");
        userCredential = new UserCredential("a", "b");
        user = new User(userCredential);
        StringsToAnswerConverter answerConverter = new StringsToAnswerConverter();
        consolePresenter = new ConsolePresenter(appConfig, answerConverter,
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
        Optional<String> newString = consolePresenter.readString();
        assertTrue(newString.isPresent());
        assertEquals(messageEn, newString.get());
    }

    @Test
    public void getUserCredentialNotEmpty() throws IOException {
        String firstName = "First Name 1234";
        String secondName = "Second name %$#";
        testConsole.printlnToStdin(firstName);
        testConsole.printlnToStdin(secondName);
        Optional<UserCredential> gotUserCredential = consolePresenter.getUserCredential();
        assertTrue(gotUserCredential.isPresent());
    }

    @Test
    public void askNumericQuestionWithRightAnswer() throws IOException {
        double rightNumber = 5.333;
        String title = "Numeric title 1";
        List<String> options = new ArrayList<>();
        NumericAnswer rightAnswer = new NumericAnswer(rightNumber);
        Question question = new NumericQuestion(title, options, rightAnswer);
        testConsole.printlnToStdin(Double.toString(rightNumber));
        Optional<? extends Answer> newAnswer = consolePresenter.readAnswer(question);
        assertTrue(rightAnswer.isEquals(newAnswer.orElse(null)));
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
        Optional<? extends Answer> newAnswer = consolePresenter.readAnswer(question);
        assertTrue(newAnswer.isPresent());
        assertFalse(rightAnswer.isEquals(newAnswer.get()));
    }
}
