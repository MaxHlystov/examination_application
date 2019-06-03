package ru.fmtk.hlystov.examinationapp.services.converter;

import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;

import java.util.List;

public interface AnswerConverter {
    Answer apply(List<String> answers);
}
