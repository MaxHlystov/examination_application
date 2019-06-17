package ru.fmtk.hlystov.examinationapp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.examination.ExamImpl;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatisticsImpl;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;
import ru.fmtk.hlystov.examinationapp.services.converter.QuestionsCSVLoader;
import ru.fmtk.hlystov.examinationapp.services.examinator.Examinator;
import ru.fmtk.hlystov.examinationapp.services.examinator.ExaminatorImpl;
import ru.fmtk.hlystov.examinationapp.services.presenter.ConsolePresenter;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@SpringBootApplication
public class Application {
    @NotNull
    private static final String STRINGS_RESOURCE_BUNDLE_NAME = "strings";
    private static ApplicationContext springContext;
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        getAppConfig().getSCVQuestionsStream().flatMap(Application::getExamByQuestionsStream)
                .ifPresent(exam -> {
                    Presenter presenter = springContext.getBean(ConsolePresenter.class);
                    ExamStatisticsImpl statistics = springContext.getBean(ExamStatisticsImpl.class);
                    Examinator examinator = new ExaminatorImpl(presenter, exam, statistics);
                    examinator.performExam();
                });
    }

    private static Optional<Exam> getExamByQuestionsStream(@NotNull InputStream inputStream) {
        ExamImpl exam = springContext.getBean(ExamImpl.class);
        QuestionsCSVLoader questionsCSVLoader = springContext.getBean(QuestionsCSVLoader.class);
        try {
            exam.addQuestions(questionsCSVLoader.readQuestions(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.of(exam);
    }

    @NotNull
    public static AppConfig getAppConfig() {
        if (appConfig == null) {
            appConfig = springContext.getBean(AppConfig.class);
        }
        return appConfig;
    }

    @Autowired
    public void setSpringContext(ApplicationContext springContext) {
        this.springContext = springContext;
    }
}
