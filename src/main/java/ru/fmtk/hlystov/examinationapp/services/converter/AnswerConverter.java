package ru.fmtk.hlystov.examinationapp.services.converter;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerConverter {
    @NotNull
    Optional<? extends Answer> apply(@NotNull List<String> answers);
}
