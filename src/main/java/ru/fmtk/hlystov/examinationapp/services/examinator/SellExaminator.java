package ru.fmtk.hlystov.examinationapp.services.examinator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.AnswerResult;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.auth.UserAuthentication;
import ru.fmtk.hlystov.examinationapp.services.auth.UserCredential;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@ShellComponent("Examinator")
@Profile("ShellConsole")
public class SellExaminator implements Examinator {
    private Presenter presenter;
    private Exam exam;
    private ExamStatistics statistics;
    private UserAuthentication userAuthentication;
    private User user;
    private int currentQuestionIdx;
    private boolean examStarted;

    @Autowired
    public void setUserAuthentication(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    @Autowired
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Autowired
    public void setStatistics(ExamStatistics statistics) {
        this.statistics = statistics;
    }

    @Autowired
    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @PostConstruct
    public void init() {
        clearState();
    }

    private void clearState() {
        currentQuestionIdx = 0;
        examStarted = false;
        user = null;
        statistics.clear();
    }

    @Override
    public void performExam() {
        presenter.showGreetengs();
    }

    @Override
    public ExamStatistics getStatistics() {
        return statistics;
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @ShellMethod(value = "Next question", key = {"next", "n"})
    public String next() {
        currentQuestionIdx++;
        return question();
    }

    @ShellMethod(value = "Previous question", key = {"prev", "p"})
    public String previous() {
        currentQuestionIdx--;
        return question();
    }

    @ShellMethod(value = "Show current question", key = {"question", "q"})
    public String question() {
        if (!examStarted) {
            startExam();
            presenter.showExamStart();
        }
        return exam.getQuestion(currentQuestionIdx)
                .map(question -> {
                    presenter.showQuestion(currentQuestionIdx + 1, question);
                    if (!statistics.answered(question)) {
                        Answer answer = presenter.readAnswer(question).orElse(null);
                        AnswerResult result = question.checkAnswers(answer);
                        presenter.showAnswerResult(result);
                        statistics.addResult(question, result);
                        return null;
                    } else {
                        return "You have answered this question!";
                    }
                }).orElse(null);
    }

    private void startExam() {
        currentQuestionIdx = 0;
        examStarted = true;
        statistics.clear();
    }

    @ShellMethod(value = "Show current statistics.", key = "stat")
    public String statistics() {
        presenter.showStatistics(statistics);
        return null;
    }

    @ShellMethod(value = "Finish exam and show results.", key = "finish")
    public String finish() {
        presenter.showStatistics(statistics);
        presenter.showExamResult(exam.getNumberToSuccess() <= statistics.getRightQuestions());
        presenter.showGoodBy();
        return null;
    }

    @ShellMethod(value = "Login as a student.", key = {"login", "l"})
    public String login(@ShellOption("--name") String firstName, @ShellOption("--family") String secondName) {
        if (firstName != null && secondName != null) {
            Optional<User> userOpt = userAuthentication.getUser(new UserCredential(firstName, secondName));
            if (userOpt.isPresent()) {
                user = userOpt.get();
                return "You login as " + user.toString();
            }
        }
        return "Wrong user login!";
    }

    public Availability loginAvailability() {
        return examStarted
                ? Availability.unavailable("Examination has started. You can't relogin.")
                : Availability.available();
    }

    public Availability questionAvailability() {
        return baseAvailability();
    }

    public Availability previousAvailability() {
        Availability availability = examAvailability();
        if (!availability.isAvailable()) {
            return availability;
        }
        if (currentQuestionIdx == 0) {
            return Availability.unavailable("You are on the first question.");
        }
        return availability;
    }

    public Availability nextAvailability() {
        Availability availability = examAvailability();
        if (!availability.isAvailable()) {
            return availability;
        }
        if (currentQuestionIdx >= exam.questionsNumber() - 1) {
            return Availability.unavailable("You are on the last question.");
        }
        return availability;
    }

    public Availability statisticsAvailability() {
        return examAvailability();
    }

    public Availability finishAvailability() {
        Availability availability = examAvailability();
        if (!availability.isAvailable()) {
            return availability;
        }
        if (statistics.getQuestionsNumber() < exam.questionsNumber()) {
            return Availability.unavailable("You doesn't answer all the questions.");
        }
        return availability;
    }

    private Availability examAvailability() {
        Availability availability = baseAvailability();
        if (!availability.isAvailable()) {
            return availability;
        }
        if (!examStarted) {
            return Availability.unavailable("Exam wasn't started. Use 'q' to show first question.");
        }
        return availability;
    }

    private Availability baseAvailability() {
        if (user == null) {
            return Availability.unavailable("You are not logged in.");
        }
        if (exam == null) {
            return Availability.unavailable("Examination information wasn't found.");
        }
        return Availability.available();
    }
}
