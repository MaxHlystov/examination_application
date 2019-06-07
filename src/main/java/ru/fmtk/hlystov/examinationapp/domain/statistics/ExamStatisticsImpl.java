package ru.fmtk.hlystov.examinationapp.domain.statistics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

public class ExamStatisticsImpl implements ExamStatistics {
    private int questionsNumber;
    private int rightQuestions;

    public ExamStatisticsImpl() {
        questionsNumber = 0;
        rightQuestions = 0;
    }

    @Override
    public void addResult(int number, @NotNull Question question, @Nullable Answer answer,
                          @NotNull AnswerResult result) {
        ++questionsNumber;
        if (result.isRight()) {
            ++rightQuestions;
        }
    }

    @Override
    public double getRightAnswersScore() {
        if (questionsNumber == 0) {
            return 0.0;
        }
        return (double) rightQuestions / questionsNumber * 100.0;
    }

    @Override
    public int getQuestionsNumber() {
        return questionsNumber;
    }

    @Override
    public int getRightQuestions() {
        return rightQuestions;
    }
}
