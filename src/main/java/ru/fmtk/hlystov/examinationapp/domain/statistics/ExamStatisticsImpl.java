package ru.fmtk.hlystov.examinationapp.domain.statistics;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;

@Service
public class ExamStatisticsImpl implements ExamStatistics {
    private int questionsNumber;
    private int rightQuestions;

    public ExamStatisticsImpl() {
        questionsNumber = 0;
        rightQuestions = 0;
    }

    @Override
    public void addResult(@NotNull AnswerResult result) {
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
