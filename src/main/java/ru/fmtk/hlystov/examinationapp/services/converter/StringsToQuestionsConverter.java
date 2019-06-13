package ru.fmtk.hlystov.examinationapp.services.converter;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.NumericQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.OptionsQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.SingleQuestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StringsToQuestionsConverter {
    @NotNull
    private final Map<String, Class<? extends Question>> classByName;
    @NotNull
    private final Map<Class<? extends Question>, QuestionConverter> questionsConverters;
    @NotNull
    private final StringsToAnswerConverter answersConverter;

    public StringsToQuestionsConverter(@NotNull StringsToAnswerConverter answersConverter) {
        this.classByName = new HashMap<>();
        this.questionsConverters = new HashMap<>();
        this.answersConverter = answersConverter;
        setBaseConverters();
    }

    @NotNull
    public Optional<? extends Question> convertQuestion(@NotNull String typeName,
                                              @NotNull String title,
                                              @NotNull List<String> options,
                                              @NotNull List<String> rightAnswers) {
        return getQuestionClassByName(typeName).flatMap(type ->
                getQuestionConverter(type).flatMap(questionConverter ->
                        answersConverter.convertAnswer(type, rightAnswers)
                                .flatMap(rightAnswer -> questionConverter.apply(title, options, rightAnswer))));
    }

    public void addConverter(@NotNull String typeName,
                             @NotNull Class<? extends Question> questionClass,
                             @NotNull QuestionConverter questionConverter) {
        classByName.put(typeName, questionClass);
        questionsConverters.put(questionClass, questionConverter);
    }

    @NotNull
    private Optional<Class<? extends Question>> getQuestionClassByName(@NotNull String typeName) {
        if (StringUtils.isEmpty(typeName)) {
            return Optional.empty();
        }
        return Optional.ofNullable(classByName.getOrDefault(typeName, null));
    }

    @NotNull
    private Optional<QuestionConverter> getQuestionConverter(@NotNull Class<? extends Question> type) {
        return Optional.ofNullable(questionsConverters.getOrDefault(type, null));
    }

    private void setBaseConverters() {
        addConverter("NUMBER_QUESTION", NumericQuestion.class,
                StringsToQuestionsConverter::createNumericQuestion);
        addConverter("SINGLE_QUESTION", SingleQuestion.class,
                StringsToQuestionsConverter::createSingleQuestion);
        addConverter("OPTIONS_QUESTION", OptionsQuestion.class,
                StringsToQuestionsConverter::createOptionsQuestion);
    }

    @NotNull
    private static Optional<NumericQuestion> createNumericQuestion(@NotNull String title,
                                                                   @NotNull List<String> options,
                                                                   @NotNull Answer rightAnswer) {
        return Optional.of(new NumericQuestion(title, options, rightAnswer));
    }

    @NotNull
    private static Optional<SingleQuestion> createSingleQuestion(@NotNull String title,
                                                                 @NotNull List<String> options,
                                                                 @NotNull Answer rightAnswer) {
        return Optional.of(new SingleQuestion(title, options, rightAnswer));
    }

    @NotNull
    private static Optional<OptionsQuestion> createOptionsQuestion(@NotNull String title,
                                                                   @NotNull List<String> options,
                                                                   @NotNull Answer rightAnswer) {
        return Optional.of(new OptionsQuestion(title, options, rightAnswer));
    }
}
