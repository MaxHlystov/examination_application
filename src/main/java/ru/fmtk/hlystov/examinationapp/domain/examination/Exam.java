package ru.fmtk.hlystov.examinationapp.domain.examination;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Exam {
    int questionsNumber();

    void clear();

    Optional<Question> getQuestion(int index);

    void addQuestion(@NotNull Question question);

    void addQuestions(@NotNull List<Question> questions);

    int getNumberToSuccess();

    Stream<Question> stream();
}
