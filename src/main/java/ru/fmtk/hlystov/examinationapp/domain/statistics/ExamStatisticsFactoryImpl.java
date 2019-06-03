package ru.fmtk.hlystov.examinationapp.domain.statistics;

import org.jetbrains.annotations.NotNull;

public class ExamStatisticsFactoryImpl implements ExamStatisticsFactory {
    @Override
    @NotNull
    public ExamStatistics createStatistics() {
        return new ExamStatisticsImpl();
    }
}
