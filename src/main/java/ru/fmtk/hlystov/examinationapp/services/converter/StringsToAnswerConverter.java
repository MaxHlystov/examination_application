package ru.fmtk.hlystov.examinationapp.services.converter;

import org.jetbrains.annotations.NotNull;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StringsToAnswerConverter {
    @NotNull
    private final Map<Class<? extends Question>, AnswerConverter> answersConverters;

    public StringsToAnswerConverter() {
        answersConverters = new HashMap<>();
        setBaseConverters();
    }

    @NotNull
    public Optional<? extends Answer> convertAnswer(@NotNull Class<? extends Question> questionClass,
                                                    @NotNull List<String> answers) {
        return getAnswerConverter(questionClass).flatMap(answerConverter -> {
            try {
                return answerConverter.apply(answers);
            } catch (IllegalArgumentException | NullPointerException ignored) {
            }
            return Optional.empty();
        });
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

    @NotNull
    private Optional<? extends AnswerConverter> getAnswerConverter(@NotNull Class<? extends Question> questionClass) {
        return Optional.ofNullable(answersConverters.getOrDefault(questionClass, null));
    }

    @NotNull
    private static Optional<NumericAnswer> stringsToNumericAnswer(@NotNull List<String> answers) {
        try {
            return Optional.of(new NumericAnswer(Float.parseFloat(answers.get(0))));
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }
        return Optional.empty();
    }

    @NotNull
    private static Optional<OptionsAnswer> stringsToOptionsAnswer(@NotNull List<String> answers) {
        try {
            return Optional.of(new OptionsAnswer(answers.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())));
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }
        return Optional.empty();
    }

    @NotNull
    private static Optional<SingleAnswer> stringsToSingleAnswer(@NotNull List<String> answers) {
        try {
            return Optional.of(new SingleAnswer(Integer.parseInt(answers.get(0))));
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }
        return Optional.empty();
    }
}
