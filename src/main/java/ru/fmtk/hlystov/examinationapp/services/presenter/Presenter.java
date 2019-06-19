package ru.fmtk.hlystov.examinationapp.services.presenter;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.auth.UserCredential;

import java.util.Optional;

public interface Presenter {
    void showMessage(@NotNull String message);

    @NotNull
    Optional<String> readString();

    void showGreetengs();

    @NotNull
    Optional<UserCredential> getUserCredential();

    void showUserNeeded();

    void showExamStart();

    @NotNull
    void showQuestion(int number, Question question);

    Optional<? extends Answer> readAnswer(@NotNull Question question);

    void showStatistics(@NotNull ExamStatistics statistics);

    void showGoodBy();

    void showAnswerResult(@NotNull AnswerResult result);

    void showExamResult(boolean success);

    @NotNull
    String getResString(@NotNull String stringName);
}
