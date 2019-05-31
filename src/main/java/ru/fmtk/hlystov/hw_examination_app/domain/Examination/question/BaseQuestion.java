package ru.fmtk.hlystov.hw_examination_app.domain.examination.question;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.Main;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.AnswerResultFactory;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswerResult;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswerResultFactory;

import java.util.List;

public abstract class BaseQuestion implements IQuestion {
    @NonNull
    private final String title;
    @NonNull
    private final List<String> options;
    @Nullable
    private IAnswer rightAnswer;

    public BaseQuestion(@NonNull String title, @NonNull List<String> options) {
        this.title = title;
        this.options = options;
    }

    @Override
    @NonNull
    public String getTitle() {
        return title;
    }

    @Override
    @NonNull
    public List<String> getOptions() {
        return options;
    }

    @Override
    @NonNull
    public IAnswerResult checkAnswers(@Nullable IAnswer answer) {
        IAnswerResultFactory resultFactory = Main.getSpringContext().getBean(AnswerResultFactory.class);
        return resultFactory.checkResult(getRightAnswer(), answer);
    }

    @Nullable
    protected IAnswer getRightAnswer() {
        return rightAnswer;
    }

    protected void setRightAnswer(@NonNull IAnswer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
