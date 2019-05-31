package ru.fmtk.hlystov.hw_examination_app.services.examinator;

import ru.fmtk.hlystov.hw_examination_app.domain.statistics.IExamStatistics;
import ru.fmtk.hlystov.hw_examination_app.services.presenter.IPresenter;

public interface IExaminator {
    void performExam();
    IExamStatistics getStatistics();
    IPresenter getPresenter();
}
