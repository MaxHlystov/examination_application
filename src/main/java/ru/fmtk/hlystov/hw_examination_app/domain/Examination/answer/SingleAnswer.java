package ru.fmtk.hlystov.hw_examination_app.domain.examination.answer;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class SingleAnswer implements IAnswer {
    @NonNull
    private final int rightOption;

    public SingleAnswer(@NonNull int rightOption) {
        this.rightOption = rightOption;
    }

    @Override
    public boolean equals(Object o) {
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
