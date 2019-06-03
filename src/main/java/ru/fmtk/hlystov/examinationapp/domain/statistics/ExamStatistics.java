package ru.fmtk.hlystov.examinationapp.domain.statistics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

public interface ExamStatistics {
    void addResult(int number, @NotNull Question question, @Nullable Answer answer, @NotNull AnswerResult result);


    double getRightAnswersScore();

    int getQuestionsNumber();

    int getRightQuestions();

}
