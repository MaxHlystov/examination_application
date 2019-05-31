package ru.fmtk.hlystov.hw_examination_app.services.examinator;

import org.springframework.lang.NonNull;
import ru.fmtk.hlystov.hw_examination_app.domain.IUser;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.IExam;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswerResult;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.IQuestion;
import ru.fmtk.hlystov.hw_examination_app.domain.statistics.IExamStatistics;
import ru.fmtk.hlystov.hw_examination_app.domain.statistics.IExamStatisticsFactory;
import ru.fmtk.hlystov.hw_examination_app.services.presenter.IPresenter;

public class Examinator implements IExaminator {
    @NonNull
    private final IPresenter presenter;
    @NonNull
    private final IExam exam;
    @NonNull
    private final IExamStatisticsFactory statisticsFactory;
    @NonNull
    private IExamStatistics statistics;

    public Examinator(@NonNull IPresenter presenter, @NonNull IExam exam,
                      @NonNull IExamStatisticsFactory statisticsFactory) {
        this.presenter = presenter;
        this.exam = exam;
        this.statisticsFactory = statisticsFactory;
    }

    @Override
    public void performExam() {
        statistics = statisticsFactory.createStatistics();
        presenter.showGreetengs();
        IUser user = presenter.getUser();
        presenter.showExamStart();
        if (user != null) {
            for (int index = 0; index < exam.questionsNumber(); ++index) {
                IQuestion question = exam.getQuestion(index);
                if (question != null) {
                    IAnswer answer = presenter.askQuestion(index + 1, question);
                    IAnswerResult result = question.checkAnswers(answer);
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
    public IExamStatistics getStatistics() {
        return statistics;
    }

    @Override
    @NonNull
    public IPresenter getPresenter() {
        return presenter;
    }


}
