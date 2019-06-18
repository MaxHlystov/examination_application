package ru.fmtk.hlystov.examinationapp.domain.statistics;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ExamStatisticsImpl implements ExamStatistics {
    private Map<Question, AnswerResult> answeredQuestions;
    private int questionsNumber;
    private int rightQuestions;

    public ExamStatisticsImpl() {
        answeredQuestions = new HashMap<>();
        questionsNumber = 0;
        rightQuestions = 0;
    }

    @Override
    public void addResult(@NotNull Question question, @NotNull AnswerResult result) {
        if (answered(question)) {
            return;
        }
        answeredQuestions.put(question, result);
        ++questionsNumber;
        if (result.isRight()) {
            ++rightQuestions;
        }
    }

    @Override
    public boolean answered(@NotNull Question question) {
        return answeredQuestions.containsKey(question);
    }

    @Override
    public Optional<AnswerResult> getAnswer(@NotNull Question question) {
        return Optional.ofNullable(answeredQuestions.getOrDefault(question, null));
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

    @Override
    public void clear() {
        answeredQuestions.clear();
        questionsNumber = 0;
        rightQuestions = 0;
    }
}
