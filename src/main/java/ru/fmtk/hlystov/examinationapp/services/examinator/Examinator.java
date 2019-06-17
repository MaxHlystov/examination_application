package ru.fmtk.hlystov.examinationapp.services.examinator;

import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

public interface Examinator {
    void performExam();
    Exam getExam();
    ExamStatistics getStatistics();
    Presenter getPresenter();
}
