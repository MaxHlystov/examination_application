package ru.fmtk.hlystov.examinationapp.domain.examination;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.*;
import java.util.function.Consumer;

@Service
@ConfigurationProperties("exam")
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

    @NotNull
    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }

    @Override
    public void forEach(Consumer<? super Question> action) {
        questions.forEach(action);
    }

    @Override
    public Spliterator<Question> spliterator() {
        return questions.spliterator();
    }

    @Override
    public int getNumberToSuccess() {
        return rightAnswersToSuccess;
    }

    public void setRightAnswersToSuccess(int rightAnswersToSuccess) {
        this.rightAnswersToSuccess = rightAnswersToSuccess;
    }
}
