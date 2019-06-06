package ru.fmtk.hlystov.examinationapp.domain.examination.answer;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NumericAnswer implements Answer {
    private final double value;
    private final double precision;

    public NumericAnswer(double value) {
        this(value, 0.005);
    }

    public NumericAnswer(double value, double precision) {
        this.value = value;
        this.precision = precision;
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
        return Math.abs(that.value - value) < this.precision;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
