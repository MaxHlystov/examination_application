package ru.fmtk.hlystov.examinationapp.services.converter;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.List;

public interface QuestionConverter {
    Question apply(@NotNull String title,
                   @NotNull List<String> options,
                   @NotNull Answer rightAnswer);
}