package ru.fmtk.hlystov.examinationapp;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.examination.ExamFactory;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;

import java.io.InputStream;
import java.io.PrintStream;

@SpringBootApplication
public class Application {
    @NotNull
    private static final String STRINGS_RESOURCE_BUNDLE_NAME = "strings";
    private static ApplicationContext springContext;
    private static AppConfig appConfig;

    @Autowired
    public void setSpringContext(ApplicationContext springContext) {
        Application.springContext = springContext;
    }

    @Autowired
    public void setAppConfig(AppConfig appConfig) { Application.appConfig = appConfig; }

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(STRINGS_RESOURCE_BUNDLE_NAME);
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean(name = "Exam")
    public Exam createExam() {
        ExamFactory factory = Application.springContext.getBean(ExamFactory.class);
        return factory.createExam();
    }

    @Bean
    public InputStream getConsoleInputStream() {
        return System.in;
    }

    @Bean
    public PrintStream getConsoleOutStream() {
        return System.out;
    }


    public static AppConfig getAppConfig() {
        return appConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
