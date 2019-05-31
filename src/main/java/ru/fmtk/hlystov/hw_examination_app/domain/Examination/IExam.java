package ru.fmtk.hlystov.hw_examination_app.domain.examination;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.IQuestion;

public interface IExam extends Iterable<IQuestion> {
    int questionsNumber();

    void clear();

    @Nullable
    IQuestion getQuestion(int index);

    void addQuestion(@NonNull IQuestion question);
}
