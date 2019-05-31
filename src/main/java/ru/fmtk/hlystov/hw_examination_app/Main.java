package ru.fmtk.hlystov.hw_examination_app;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.ExamCSVFactory;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.IExam;
import ru.fmtk.hlystov.hw_examination_app.domain.statistics.ExamStatisticsFactory;
import ru.fmtk.hlystov.hw_examination_app.domain.statistics.IExamStatisticsFactory;
import ru.fmtk.hlystov.hw_examination_app.services.AppContext;
import ru.fmtk.hlystov.hw_examination_app.services.examinator.Examinator;
import ru.fmtk.hlystov.hw_examination_app.services.examinator.IExaminator;
import ru.fmtk.hlystov.hw_examination_app.services.presenter.ConsolePresenter;
import ru.fmtk.hlystov.hw_examination_app.services.presenter.IPresenter;


public class Main {
    @NonNull
    private static final String SPRING_CONTEXT_RESOURCE_NAME = "/spring-context.xml";
    @Nullable
    private static ClassPathXmlApplicationContext springContext;
    @Nullable
    private static AppContext appContext;

    public static void main(String[] args) {
        IPresenter presenter = getSpringContext().getBean(ConsolePresenter.class);
        ExamCSVFactory examFactory = getSpringContext().getBean(ExamCSVFactory.class);
        IExam exam = examFactory.createExam();
        IExamStatisticsFactory statisticsFactory = getSpringContext().getBean(ExamStatisticsFactory.class);
        IExaminator examinator = new Examinator(presenter, exam, statisticsFactory);
        examinator.performExam();
    }

    @NonNull
    public static ClassPathXmlApplicationContext getSpringContext() {
        if (springContext == null) {
            springContext = new ClassPathXmlApplicationContext(SPRING_CONTEXT_RESOURCE_NAME);
        }
        return springContext;
    }

    @NonNull
    public static AppContext getAppContext() {
        if (appContext == null) {
            appContext = getSpringContext().getBean(AppContext.class);
        }
        return appContext;
    }
}
