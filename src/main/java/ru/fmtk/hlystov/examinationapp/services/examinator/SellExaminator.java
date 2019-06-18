package ru.fmtk.hlystov.examinationapp.services.examinator;

import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

@ShellComponent("Examinator")
@Profile("Shell")
public class SellExaminator implements Examinator {
    @Override
    public void performExam() {
        System.out.println("Sell examinator: did an exam.");
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
