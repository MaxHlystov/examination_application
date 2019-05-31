package ru.fmtk.hlystov.hw_examination_app.domain.examination.question;

public enum QuestionType {
    NUMBER_QUESTION(NumberQuestion.class),
    OPTIONS_QUESTION(OptionsQuestion.class),
    SINGLE_QUESTION(SingleQuestion.class);

    QuestionType(Class<? extends IQuestion> questionClass) {
    }

}
