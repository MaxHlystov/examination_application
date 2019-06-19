package ru.fmtk.hlystov.examinationapp.services.examinator;


import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

public class ExaminatorStub implements Examinator {
    @Override
    public void performExam() {

    }

    @Override
    public ExamStatistics getStatistics() {
        return null;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }
}
