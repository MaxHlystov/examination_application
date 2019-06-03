package ru.fmtk.hlystov.examinationapp.domain.examination.answer;


import org.jetbrains.annotations.Nullable;

public interface Answer {
    boolean isEquals(@Nullable Answer answer);
}
