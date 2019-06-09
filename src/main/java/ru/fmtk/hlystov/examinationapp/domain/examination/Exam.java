package ru.fmtk.hlystov.examinationapp.domain.examination;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.util.List;
import java.util.Optional;

public interface Exam extends Iterable<Question> {
    int questionsNumber();

    void clear();

    Optional<Question> getQuestion(int index);

    void addQuestion(@NotNull Question question);

    void addQuestions(@NotNull List<Question> questions);

    int getNumberToSuccess();
}
