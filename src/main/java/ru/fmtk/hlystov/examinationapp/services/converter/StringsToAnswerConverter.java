package ru.fmtk.hlystov.examinationapp.services.converter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.NumericAnswer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.OptionsAnswer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.SingleAnswer;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.NumericQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.OptionsQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.SingleQuestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StringsToAnswerConverter {
    @NotNull
    private final Map<Class<? extends Question>, AnswerConverter> answersConverters;

    public StringsToAnswerConverter() {
        answersConverters = new HashMap<>();
        setBaseConverters();
    }

    @Nullable
    public Answer convertAnswer(@NotNull Class<? extends Question> questionClass,
                                @NotNull List<String> answers) {
        AnswerConverter answerConverter = getAnswerConverter(questionClass);
        if (answerConverter != null) {
            try {
                return answerConverter.apply(answers);
            } catch (IllegalArgumentException | NullPointerException ignored) {
            }
        }
        return null;
    }

    public void addConverter(@NotNull Class<? extends Question> questionClass,
                             @NotNull AnswerConverter answerConverter) {
        answersConverters.put(questionClass, answerConverter);
    }

    private void setBaseConverters() {
        addConverter(NumericQuestion.class, StringsToAnswerConverter::stringsToNumericAnswer);
        addConverter(SingleQuestion.class, StringsToAnswerConverter::stringsToSingleAnswer);
        addConverter(OptionsQuestion.class, StringsToAnswerConverter::stringsToOptionsAnswer);
    }

    @Nullable
    private AnswerConverter getAnswerConverter(@NotNull Class<? extends Question> questionClass) {
        return answersConverters.getOrDefault(questionClass, null);
    }

    @Nullable
    private static NumericAnswer stringsToNumericAnswer(@NotNull List<String> answers) {
        try {
            return new NumericAnswer(Float.parseFloat(answers.get(0)));
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }
        return null;
    }

    @Nullable
    private static OptionsAnswer stringsToOptionsAnswer(@NotNull List<String> answers) {
        try {
            return new OptionsAnswer(answers.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }
        return null;
    }

    @Nullable
    private static SingleAnswer stringsToSingleAnswer(@NotNull List<String> answers) {
        try {
            return new SingleAnswer(Integer.parseInt(answers.get(0)));
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }
        return null;
    }
}
