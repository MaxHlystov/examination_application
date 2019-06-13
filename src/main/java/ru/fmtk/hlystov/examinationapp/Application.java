package ru.fmtk.hlystov.examinationapp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.fmtk.hlystov.examinationapp.domain.examination.ExamImpl;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatisticsImpl;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;
import ru.fmtk.hlystov.examinationapp.services.converter.QuestionsCSVLoader;
import ru.fmtk.hlystov.examinationapp.services.examinator.Examinator;
import ru.fmtk.hlystov.examinationapp.services.examinator.ExaminatorImpl;
import ru.fmtk.hlystov.examinationapp.services.presenter.ConsolePresenter;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

import java.io.IOException;

@Configuration
@ComponentScan
@PropertySource("/properties")
public class Application {
    @NotNull
    private static final String STRINGS_RESOURCE_BUNDLE_NAME = "strings";
    @Nullable
    private static volatile AnnotationConfigApplicationContext springContext;
    @Nullable
    private static AppConfig appConfig;

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms =
                new ReloadableResourceBundleMessageSource();
        ms.setBasename(STRINGS_RESOURCE_BUNDLE_NAME);
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    public static void main(String[] args) throws IOException {
        Presenter presenter = getSpringContext().getBean(ConsolePresenter.class);
        QuestionsCSVLoader questionsCSVLoader = getSpringContext().getBean(QuestionsCSVLoader.class);
        ExamImpl exam = getSpringContext().getBean(ExamImpl.class);
        exam.addQuestions(questionsCSVLoader.readQuestions(
                getAppConfig().getSCVQuestionsStream()));
        ExamStatisticsImpl statistics = getSpringContext().getBean(ExamStatisticsImpl.class);
        Examinator examinator = new ExaminatorImpl(presenter, exam, statistics);
        examinator.performExam();
    }

    @NotNull
    public static AppConfig getAppConfig() {
        if (appConfig == null) {
            appConfig = getSpringContext().getBean(AppConfig.class);
        }
        return appConfig;
    }

    @NotNull
    private static AnnotationConfigApplicationContext getSpringContext() {
        if (springContext == null) {
            synchronized (Application.class) {
                if (springContext == null) {
                    springContext = new AnnotationConfigApplicationContext(Application.class);
                }
            }
        }
        return springContext;
    }
}
