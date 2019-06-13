package ru.fmtk.hlystov.examinationapp.domain.statistics;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;

public interface ExamStatistics {
    void addResult(@NotNull AnswerResult result);


    double getRightAnswersScore();

    int getQuestionsNumber();

    int getRightQuestions();

}
