package ru.fmtk.hlystov.hw_examination_app.domain.examination.answer;

import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OptionsAnswer implements IAnswer {
    @NonNull
    private final Set<Integer> rightOptions;

    public OptionsAnswer(@NonNull List<Integer> rightOptions) {
        this.rightOptions = new HashSet<>(rightOptions);
    }

    @Override
    public boolean equals(Object o) {
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
