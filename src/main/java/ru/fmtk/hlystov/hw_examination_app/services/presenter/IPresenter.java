package ru.fmtk.hlystov.hw_examination_app.services.presenter;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.domain.IUser;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswerResult;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.IQuestion;
import ru.fmtk.hlystov.hw_examination_app.domain.statistics.IExamStatistics;

public interface IPresenter {
    void showMessage(@NonNull String message);

    @Nullable
    String readString();

    void showGreetengs();

    @Nullable
    IUser getUser();

    void showUserNeeded();

    void showExamStart();

    @Nullable
    IAnswer askQuestion(int number, IQuestion question);

    void showStatistics(@NonNull IExamStatistics statistics);

    void showGoodBy();

    void showAnswerResult(@NonNull IAnswerResult result);
}
