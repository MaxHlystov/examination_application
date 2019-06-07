package ru.fmtk.hlystov.examinationapp.domain.examination.question;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.fmtk.hlystov.examinationapp.Main;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResultImpl;
import ru.fmtk.hlystov.examinationapp.services.LocalizationService;

import java.util.List;

public abstract class BaseQuestion implements Question {
    @NotNull
    private final String title;
    @NotNull
    private final List<String> options;
    @NotNull
    private final Answer rightAnswer;

    public BaseQuestion(@NotNull String title, @NotNull List<String> options,
                        @NotNull Answer rightAnswer) {
        this.title = title;
        this.options = options;
        this.rightAnswer = rightAnswer;
    }

    @Override
    @NotNull
    public String getTitle() {
        return title;
    }

    @Override
    @NotNull
    public List<String> getOptions() {
        return options;
    }

    @Override
    @NotNull
    public AnswerResult checkAnswers(@Nullable Answer answer) {
        LocalizationService localizationService = Main.getLocalizationService();
        StringBuilder sb = new StringBuilder();
        boolean equals = rightAnswer.isEquals(answer);
        if (equals) {
            sb.append(localizationService.getString("answer-result-factory.ok"));
        } else {
            sb.append(localizationService.getString("answer-result-factory.wrong"));
        }
        return new AnswerResultImpl(sb.toString(), equals);
    }

    @NotNull
    protected Answer getRightAnswer() {
        return rightAnswer;
    }
}
