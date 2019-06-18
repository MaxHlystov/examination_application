package ru.fmtk.hlystov.examinationapp.domain.statistics;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.Optional;

public interface ExamStatistics {
    void addResult(@NotNull Question question, @NotNull AnswerResult result);

    Optional<AnswerResult> getAnswer(@NotNull Question question);

    boolean answered(@NotNull Question question);

    double getRightAnswersScore();

    int getQuestionsNumber();

    int getRightQuestions();

    void clear();

}
