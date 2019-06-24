package ru.fmtk.hlystov.examinationapp.domain.examination.question;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;

import java.util.List;

public class OptionsQuestion extends BaseQuestion {
    public OptionsQuestion(@NotNull String title,
                           @NotNull List<String> options,
                           @NotNull Answer rightAnswer) {
        super(title, options, rightAnswer);
    }
}
