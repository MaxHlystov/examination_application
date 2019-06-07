package ru.fmtk.hlystov.examinationapp.domain.examination.answer;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SingleAnswer implements Answer {
    private final int rightOption;

    public SingleAnswer(int rightOption) {
        this.rightOption = rightOption;
    }

    @Override
    public boolean isEquals(@Nullable Answer answer) {
        return this.equals(answer);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleAnswer that = (SingleAnswer) o;
        return rightOption == that.rightOption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rightOption);
    }
}
