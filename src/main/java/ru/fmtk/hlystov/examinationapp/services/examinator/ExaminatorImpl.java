package ru.fmtk.hlystov.examinationapp.services.examinator;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

import java.util.AbstractMap;
import java.util.Optional;
import java.util.stream.IntStream;

@Profile("console")
@Component
public class ExaminatorImpl implements Examinator {
    @NotNull
    private final Presenter presenter;
    @NotNull
    private final Exam exam;
    @NotNull
    private final ExamStatistics statistics;

    @Autowired
    public ExaminatorImpl(@NotNull Presenter presenter, @NotNull Exam exam,
                          @NotNull ExamStatistics statistics) {
        this.presenter = presenter;
        this.exam = exam;
        this.statistics = statistics;
    }

    @Override
    public void performExam() {
        presenter.showGreetengs();
        Optional<User> optUser = presenter.getUser();
        presenter.showExamStart();
        optUser.ifPresentOrElse(
                user -> {
                    askQuestions();
                    presenter.showStatistics(statistics);
                    presenter.showExamResult(exam.getNumberToSuccess() <= statistics.getRightQuestions());
                },
                presenter::showUserNeeded);
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

    private void askQuestions() {
        IntStream.range(0, exam.questionsNumber())
                .mapToObj(index -> new AbstractMap.SimpleEntry<>(index, exam.getQuestion(index)))
                .filter(pair -> pair.getValue().isPresent())
                .forEachOrdered(pair -> askQuestion(pair.getKey(), pair.getValue().get()));
    }

    private void askQuestion(int index, @NotNull Question question) {
        Answer answer = presenter.askQuestion(index + 1, question).orElse(null);
        AnswerResult result = question.checkAnswers(answer);
        presenter.showAnswerResult(result);
        statistics.addResult(result);
    }
}
