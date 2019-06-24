package ru.fmtk.hlystov.examinationapp.domain.examination;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ExamImpl implements Exam {
    @NotNull
    private final List<Question> questions;
    private int rightAnswersToSuccess;

    public ExamImpl() {
        this.questions = new ArrayList<>();
    }

    @Override
    public int questionsNumber() {
        return questions.size();
    }


    @Override
    public void clear() {
        questions.clear();
    }

    @Override
    public Optional<Question> getQuestion(int index) {
        if (index >= 0 && index < questionsNumber()) {
            return Optional.of(questions.get(index));
        }
        return Optional.empty();
    }

    @Override
    public void addQuestion(@NotNull Question question) {
        questions.add(question);
    }

    @Override
    public void addQuestions(@NotNull List<Question> questions) {
        this.questions.addAll(questions);
    }

    @Override
    public Stream<Question> stream() {
        return questions.stream();
    }

    @Override
    public int getNumberToSuccess() {
        return rightAnswersToSuccess;
    }

    public void setRightAnswersToSuccess(int rightAnswersToSuccess) {
        this.rightAnswersToSuccess = rightAnswersToSuccess;
    }
}
