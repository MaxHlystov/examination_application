package ru.fmtk.hlystov.examinationapp.domain.examination;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

public interface Exam extends Iterable<Question> {
    int questionsNumber();

    void clear();

    @Nullable
    Question getQuestion(int index);

    void addQuestion(@NotNull Question question);
}
