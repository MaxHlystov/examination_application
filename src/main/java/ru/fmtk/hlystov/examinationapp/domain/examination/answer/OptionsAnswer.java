package ru.fmtk.hlystov.examinationapp.domain.examination.answer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OptionsAnswer implements Answer {
    @NotNull
    private final Set<Integer> rightOptions;

    public OptionsAnswer(@NotNull List<Integer> rightOptions) {
        this.rightOptions = new HashSet<>(rightOptions);
    }

    @Override
    public boolean isEquals(@Nullable Answer other) {
        return this.equals(other);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionsAnswer optionsAnswer = (OptionsAnswer) o;
        return rightOptions.equals(optionsAnswer.rightOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rightOptions);
    }
}
