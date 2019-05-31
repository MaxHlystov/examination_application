package ru.fmtk.hlystov.hw_examination_app.domain.statistics;

import org.springframework.lang.NonNull;

public class ExamStatisticsFactory implements IExamStatisticsFactory {
    @Override
    @NonNull
    public IExamStatistics createStatistics() {
        return new ExamStatistics();
    }
}
