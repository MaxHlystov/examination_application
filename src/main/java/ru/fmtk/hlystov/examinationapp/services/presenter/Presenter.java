package ru.fmtk.hlystov.examinationapp.services.presenter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;

public interface Presenter {
    void showMessage(@NotNull String message);

    @Nullable
    String readString();

    void showGreetengs();

    @Nullable
    User getUser();

    void showUserNeeded();

    void showExamStart();

    @Nullable
    Answer askQuestion(int number, Question question);

    void showStatistics(@NotNull ExamStatistics statistics);

    void showGoodBy();

    void showAnswerResult(@NotNull AnswerResult result);

    void showExamResult(boolean success);
}
