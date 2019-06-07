package ru.fmtk.hlystov.examinationapp.domain.examination.question;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;

import java.util.List;

public interface Question {
    @NotNull
    String getTitle();

    @NotNull
    List<String> getOptions();

    @NotNull
    AnswerResult checkAnswers(@Nullable Answer answer);
}
