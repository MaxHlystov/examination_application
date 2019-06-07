package ru.fmtk.hlystov.examinationapp.domain.examination;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ExamImpl implements Exam {
    @NotNull
    private final List<Question> questions;

    public ExamImpl() {
        questions = new ArrayList<>();
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
    @Nullable
    public Question getQuestion(int index) {
        if (index < questionsNumber()) {
            return questions.get(index);
        }
        return null;
    }

    @Override
    public void addQuestion(@NotNull Question question) {
        questions.add(question);
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
}
