package ru.fmtk.hlystov.hw_examination_app.services.presenter;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import ru.fmtk.hlystov.hw_examination_app.Main;
import ru.fmtk.hlystov.hw_examination_app.domain.IUser;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswerResult;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.IQuestion;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.NumberQuestion;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.OptionsQuestion;
import ru.fmtk.hlystov.hw_examination_app.domain.statistics.IExamStatistics;
import ru.fmtk.hlystov.hw_examination_app.services.AppContext;
import ru.fmtk.hlystov.hw_examination_app.services.auth.IUserAuthentification;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsolePresenter implements IPresenter {
    @NonNull
    private final IUserAuthentification userAuthentification;
    @NonNull
    private final Scanner sc;
    @NonNull
    private final PrintStream out;
    @NonNull
    private AppContext appContext;

    public ConsolePresenter(IUserAuthentification userAuthentification) {
        this(userAuthentification, System.in, System.out);
    }

    public ConsolePresenter(@NonNull IUserAuthentification userAuthentification,
                            @NonNull InputStream in, @NonNull PrintStream out) {
        this.userAuthentification = userAuthentification;
        this.sc = new Scanner(in);
        this.out = out;
    }

    @Override
    public void showMessage(@NonNull String message) {
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
    public IUser getUser() {
        return userAuthentification.getUser();
    }

    @Override
    @Nullable
    public IAnswer askQuestion(int number, @NonNull IQuestion question) {
        showMessage(getResString("Presenter__longLine"));
        String questionPrompt = getResString("Presenter__questionNumber");
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
            return question.stringsToAnswer(answers);
        }
        showMessage(getResString("Presenter__longLine"));
        return null;
    }

    @Override
    public void showStatistics(@NonNull IExamStatistics statistics) {
        showMessage(getResString("Presenter__longLine"));
        showMessage(getResString("Presenter__statisticsStart"));
        showMessage(String.format(getResString("Presenter__statisticsAggregate"),
                statistics.getQuestionsNumber(),
                statistics.getRightQuestions(),
                statistics.getRightAnswersScore()));
    }

    @Override
    public void showExamStart() {
    }

    @Override
    public void showGreetengs() {
        showMessage(getResString("Presenter__greetings"));
    }

    @Override
    public void showUserNeeded() {
        showMessage(getResString("Presenter__errorNeedAUser"));
    }

    @Override
    public void showGoodBy() {
        showMessage(getResString("Presenter__bay"));
    }

    @Override
    public void showAnswerResult(IAnswerResult result) {
        showMessage(String.format(getResString("Presenter__answerResult"),
                result.getDescription()));
    }

    @NonNull
    private String getInputPrompt(Class<? extends IQuestion> aClass) {
        if (aClass.equals(NumberQuestion.class)) {
            return getResString("Presenter__numberPrompt");
        }
        if (aClass.equals(OptionsQuestion.class)) {
            return getResString("Presenter__optionsPrompt");
        }
        return getResString("Presenter__optionPrompt");
    }

    private void showOptions(@NonNull List<String> options) {
        for (int i = 0; i < options.size(); ++i) {
            showMessage(String.format("  %d. %s", i + 1, options.get(i)));
        }

    }

    @NonNull
    String getResString(@NonNull String stringName) {
        if (appContext == null) {
            appContext = Main.getAppContext();
        }
        return appContext.getString(stringName);
    }
}
