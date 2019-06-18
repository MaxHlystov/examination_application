package ru.fmtk.hlystov.examinationapp.domain.examination;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.services.AppConfig;
import ru.fmtk.hlystov.examinationapp.services.converter.QuestionsCSVLoader;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ExamFactory {
    private ApplicationContext springContext;
    private AppConfig appConfig;
    QuestionsCSVLoader questionsCSVLoader;

    @Autowired
    public void setSpringContext(ApplicationContext springContext) {
        this.springContext = springContext;
    }

    @Autowired
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Autowired
    public void setQuestionsCSVLoader(QuestionsCSVLoader questionsCSVLoader) {
        this.questionsCSVLoader = questionsCSVLoader;
    }

    public Exam createExam() {
        Exam exam = new ExamImpl();
        appConfig.getSCVQuestionsStream()
                .ifPresent(is -> fillExamByQuestionsStream(exam, is));
        return exam;
    }

    private void fillExamByQuestionsStream(@NotNull Exam exam,
                                           @NotNull InputStream inputStream) {
        try {
            exam.addQuestions(questionsCSVLoader.readQuestions(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
