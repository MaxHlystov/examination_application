package ru.fmtk.hlystov.examinationapp.domain.examination.answer;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NumericAnswer implements Answer {
    private final float rightAnswer;

    public NumericAnswer(float rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean isEquals(@Nullable Answer other) {
        return this.equals(other);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumericAnswer that = (NumericAnswer) o;
        return Float.compare(that.rightAnswer, rightAnswer) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rightAnswer);
    }
}
