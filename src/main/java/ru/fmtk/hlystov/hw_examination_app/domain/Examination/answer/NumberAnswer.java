package ru.fmtk.hlystov.hw_examination_app.domain.examination.answer;

import java.util.Objects;

public class NumberAnswer implements IAnswer {
    private final float rightAnswer;

    public NumberAnswer(float rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberAnswer that = (NumberAnswer) o;
        return Float.compare(that.rightAnswer, rightAnswer) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rightAnswer);
    }
}
