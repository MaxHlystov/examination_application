package ru.fmtk.hlystov.hw_examination_app.domain.statistics;

import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswerResult;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.IQuestion;

public class ExamStatistics implements IExamStatistics {
    private int questionsNumber;
    private int rightQuestions;

    public ExamStatistics() {
        questionsNumber = 0;
        rightQuestions = 0;
    }

    @Override
    public void addResult(int number, IQuestion question, IAnswer answer, IAnswerResult result) {
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
