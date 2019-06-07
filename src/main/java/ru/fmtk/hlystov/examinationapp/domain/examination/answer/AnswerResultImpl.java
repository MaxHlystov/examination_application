package ru.fmtk.hlystov.examinationapp.domain.examination.answer;


import org.jetbrains.annotations.Nullable;

public class AnswerResultImpl implements AnswerResult {
    @Nullable
    private final String description;
    private final boolean right;

    public AnswerResultImpl(@Nullable String description, boolean right) {
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
