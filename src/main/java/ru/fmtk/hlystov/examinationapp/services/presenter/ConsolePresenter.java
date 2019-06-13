package ru.fmtk.hlystov.examinationapp.services.presenter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.NumericQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.OptionsQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;
import ru.fmtk.hlystov.examinationapp.services.auth.UserAuthentification;
import ru.fmtk.hlystov.examinationapp.services.converter.StringsToAnswerConverter;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class ConsolePresenter implements Presenter {
    @NotNull
    private final AppConfig appConfig;
    @NotNull
    private final UserAuthentification userAuthentification;
    @NotNull
    private final StringsToAnswerConverter answerConverter;
    @NotNull
    private final Scanner sc;
    @NotNull
    private final PrintStream out;

    @Autowired
    public ConsolePresenter(@NotNull AppConfig appConfig,
                            @Qualifier("consoleUserAuth") @NotNull UserAuthentification userAuthentification,
                            @NotNull StringsToAnswerConverter answerConverter) {
        this(appConfig, userAuthentification, answerConverter, System.in, System.out);
    }

    public ConsolePresenter(@NotNull AppConfig appConfig,
                            @NotNull UserAuthentification userAuthentification,
                            @NotNull StringsToAnswerConverter answerConverter,
                            @NotNull InputStream in,
                            @NotNull PrintStream out) {
        this.appConfig = appConfig;
        this.userAuthentification = userAuthentification;
        this.answerConverter = answerConverter;
        this.sc = new Scanner(in);
        this.out = out;
    }

    @Override
    public void showMessage(@NotNull String message) {
        out.println(message);
    }

    @Override
    @Nullable
    public String readString() {
        try {
            return sc.nextLine();
        } catch (NoSuchElementException | IllegalStateException ignoredToNull) {
        }
        return null;
    }

    @Override
    public User getUser() {
        return userAuthentification.getUser();
    }

    @Override
    @Nullable
    public Answer askQuestion(int number, @NotNull Question question) {
        showMessage(getResString("presenter.long-ine"));
        String questionPrompt = getResString("presenter.question-number");
        showMessage(String.format(questionPrompt, number));
        showMessage(question.getTitle());
        showOptions(question.getOptions());
        showMessage(getInputPrompt(question.getClass()));
        String textAnswer = readString();
        List<String> answers;
        if (textAnswer != null) {
            answers = Arrays.stream(textAnswer.split(" "))
                    .filter(s -> !StringUtils.isEmpty(s))
                    .collect(Collectors.toList());
            return answerConverter.convertAnswer(question.getClass(), answers);
        }
        showMessage(getResString("presenter.long-ine"));
        return null;
    }

    @Override
    public void showStatistics(@NotNull ExamStatistics statistics) {
        showMessage(getResString("presenter.long-ine"));
        showMessage(getResString("presenter.statistics-start"));
        showMessage(String.format(getResString("presenter.statistics-aggregate"),
                statistics.getQuestionsNumber(),
                statistics.getRightQuestions(),
                statistics.getRightAnswersScore()));
    }

    @Override
    public void showExamStart() {
        showMessage(getResString("presenter.exam-start"));
    }

    @Override
    public void showGreetengs() {
        showMessage(getResString("presenter.greetings"));
    }

    @Override
    public void showUserNeeded() {
        showMessage(getResString("presenter-error-need-user"));
    }

    @Override
    public void showGoodBy() {
        showMessage(getResString("presenter-bay"));
    }

    @Override
    public void showAnswerResult(@NotNull AnswerResult result) {
        showMessage(String.format(getResString("presenter.answerResult"),
                result.getDescription()));
    }

    @Override
    public void showExamResult(boolean success) {
        showMessage(getResString("presenter.long-ine"));
        showMessage(getResString(success ? "presenter.exam-success" : "presenter.exam-unsuccess"));
    }

    @NotNull
    private String getInputPrompt(Class<? extends Question> aClass) {
        if (aClass.equals(NumericQuestion.class)) {
            return getResString("presenter.number-prompt");
        }
        if (aClass.equals(OptionsQuestion.class)) {
            return getResString("presenter.options-prompt");
        }
        return getResString("presenter.option-prompt");
    }

    private void showOptions(@NotNull List<String> options) {
        for (int i = 0; i < options.size(); ++i) {
            showMessage(String.format("  %d. %s", i + 1, options.get(i)));
        }

    }

    @NotNull
    String getResString(@NotNull String stringName) {
        String message = appConfig.getMessage(stringName, null);
        return message == null ? "" : message;
    }
}
