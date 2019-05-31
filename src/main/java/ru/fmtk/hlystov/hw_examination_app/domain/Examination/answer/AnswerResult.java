package ru.fmtk.hlystov.hw_examination_app.domain.examination.answer;


import org.springframework.lang.Nullable;

public class AnswerResult implements IAnswerResult {
    @Nullable
    private final String description;
    private final boolean right;

    public AnswerResult(@Nullable String description, boolean right) {
        this.description = description;
        this.right = right;
    }

    @Override
    public String getDescription() {
        if (description == null) {
            return "";
        }
        return description;
    }

    @Override
    public boolean isRight() {
        return right;
    }
}
