package ru.fmtk.hlystov.examinationapp.services.converter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    @Nullable
    public Question convertQuestion(@NotNull String typeName,
                                    @NotNull String title,
                                    @NotNull List<String> options,
                                    @NotNull List<String> rightAnswers) {
        Class<? extends Question> type = getQuestionClassByName(typeName);
        if (type == null) {
            return null;
        }
        QuestionConverter questionConverter = getQuestionConverter(type);
        if (questionConverter == null) {
            return null;
        }
        Answer rightAnswer = answersConverter.convertAnswer(type, rightAnswers);
        if (rightAnswer == null) {
            return null;
        }
        return questionConverter.apply(title, options, rightAnswer);
    }

    public void addConverter(@NotNull String typeName,
                             @NotNull Class<? extends Question> questionClass,
                             @NotNull QuestionConverter questionConverter) {
        classByName.put(typeName, questionClass);
        questionsConverters.put(questionClass, questionConverter);
    }

    @Nullable
    private Class<? extends Question> getQuestionClassByName(@NotNull String typeName) {
        if (StringUtils.isEmpty(typeName)) {
            return null;
        }
        return classByName.getOrDefault(typeName, null);
    }

    @Nullable
    private QuestionConverter getQuestionConverter(@NotNull Class<? extends Question> type) {
        return questionsConverters.getOrDefault(type, null);
    }

    private void setBaseConverters() {
        addConverter("NUMBER_QUESTION", NumericQuestion.class, NumericQuestion::new);
        addConverter("SINGLE_QUESTION", SingleQuestion.class, SingleQuestion::new);
        addConverter("OPTIONS_QUESTION", OptionsQuestion.class, OptionsQuestion::new);
    }
}
