package ru.fmtk.hlystov.examinationapp.services.examinator;

import org.jetbrains.annotations.NotNull;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

public class ExaminatorImpl implements Examinator {
    @NotNull
    private final Presenter presenter;
    @NotNull
    private final Exam exam;
    @NotNull
    private ExamStatistics statistics;

    public ExaminatorImpl(@NotNull Presenter presenter, @NotNull Exam exam,
                          @NotNull ExamStatistics statistics) {
        this.presenter = presenter;
        this.exam = exam;
        this.statistics = statistics;
    }

    @Override
    public void performExam() {
        presenter.showGreetengs();
        User user = presenter.getUser();
        presenter.showExamStart();
        if (user != null) {
            for (int index = 0; index < exam.questionsNumber(); ++index) {
                Question question = exam.getQuestion(index);
                if (question != null) {
                    Answer answer = presenter.askQuestion(index + 1, question);
                    AnswerResult result = question.checkAnswers(answer);
                    presenter.showAnswerResult(result);
                    statistics.addResult(index + 1, question, answer, result);
                }

            }
            presenter.showStatistics(statistics);
        } else {
            presenter.showUserNeeded();
        }
        presenter.showGoodBy();
    }

    @Override
    @NotNull
    public ExamStatistics getStatistics() {
        return statistics;
    }

    @Override
    @NotNull
    public Presenter getPresenter() {
        return presenter;
    }


}
