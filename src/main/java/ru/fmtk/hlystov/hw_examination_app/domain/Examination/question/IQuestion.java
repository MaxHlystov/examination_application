package ru.fmtk.hlystov.hw_examination_app.domain.examination.question;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswerResult;

import java.util.List;

public interface IQuestion {
    @NonNull
    String getTitle();

    @NonNull
    List<String> getOptions();

    @Nullable
    IAnswer stringsToAnswer(@NonNull List<String> answers);

    @NonNull
    IAnswerResult checkAnswers(@Nullable IAnswer answer);
}
