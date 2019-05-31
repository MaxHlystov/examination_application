package ru.fmtk.hlystov.hw_examination_app.domain.statistics;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswerResult;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.IQuestion;

public interface IExamStatistics {
    void addResult(int number, @NonNull IQuestion question, @Nullable IAnswer answer, @NonNull IAnswerResult result);


    double getRightAnswersScore();

    int getQuestionsNumber();

    int getRightQuestions();

}
