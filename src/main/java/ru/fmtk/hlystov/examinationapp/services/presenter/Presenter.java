package ru.fmtk.hlystov.examinationapp.services.presenter;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;

import java.util.Optional;

public interface Presenter {
    void showMessage(@NotNull String message);

    @NotNull
    Optional<String> readString();

    void showGreetengs();

    @NotNull
    Optional<User> getUser();

    void showUserNeeded();

    void showExamStart();

    @NotNull
    Optional<? extends Answer> askQuestion(int number, Question question);

    void showStatistics(@NotNull ExamStatistics statistics);

    void showGoodBy();

    void showAnswerResult(@NotNull AnswerResult result);

    void showExamResult(boolean success);
}
