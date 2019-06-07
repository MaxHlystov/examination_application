package ru.fmtk.hlystov.examinationapp.domain.examination.question;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;

import java.util.List;

public class NumericQuestion extends BaseQuestion {
    public NumericQuestion(@NotNull String title, @NotNull List<String> options, @NotNull Answer rightAnswer) {
        super(title, options, rightAnswer);
    }
}
