package ru.fmtk.hlystov.examinationapp.domain.examination;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.*;
import java.util.function.Consumer;

@Service
public class ExamImpl implements Exam {
    @NotNull
    private final List<Question> questions;
    private final int numberToSuccess;

    public ExamImpl(@Value("${right.answer.for.success}") int numberToSuccess) {
        this.questions = new ArrayList<>();
        this.numberToSuccess = numberToSuccess;
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
        if (index < questionsNumber()) {
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
        return numberToSuccess;
    }
}
