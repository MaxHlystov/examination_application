package ru.fmtk.hlystov.examinationapp.services.examinator;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.auth.UserAuthentication;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

import java.util.AbstractMap;
import java.util.Optional;
import java.util.stream.IntStream;

@Component("Examinator")
@Profile("PureConsole")
public class ExaminatorImpl implements Examinator, ApplicationRunner {
    private final Presenter presenter;
    private final Exam exam;
    private final ExamStatistics statistics;
    private final UserAuthentication userAuthentication;

    public ExaminatorImpl(Presenter presenter, Exam exam, ExamStatistics statistics, UserAuthentication userAuthentication) {
        this.presenter = presenter;
        this.exam = exam;
        this.statistics = statistics;
        this.userAuthentication = userAuthentication;
    }

    @Override
    public void run(ApplicationArguments args) {
        performExam();
    }

    @Override
    public void performExam() {
        presenter.showGreetengs();
        Optional<User> optUser = presenter.getUserCredential().flatMap(userAuthentication::getUser);
        if (optUser.isPresent()) {
            presenter.showExamStart();
            askQuestions();
            presenter.showStatistics(statistics);
            presenter.showExamResult(exam.getNumberToSuccess() <= statistics.getRightQuestions());
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

    private void askQuestions() {
        IntStream.range(0, exam.questionsNumber())
                .mapToObj(index -> new AbstractMap.SimpleEntry<>(index, exam.getQuestion(index)))
                .filter(pair -> pair.getValue().isPresent())
                .forEachOrdered(pair -> askQuestion(pair.getKey(), pair.getValue().get()));
    }

    private void askQuestion(int index, @NotNull Question question) {
        presenter.showQuestion(index + 1, question);
        Answer answer = presenter.readAnswer(question).orElse(null);
        AnswerResult result = question.checkAnswers(answer);
        presenter.showAnswerResult(result);
        statistics.addResult(question, result);
    }
}
