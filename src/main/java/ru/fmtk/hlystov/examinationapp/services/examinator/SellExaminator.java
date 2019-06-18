package ru.fmtk.hlystov.examinationapp.services.examinator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.statistics.ExamStatistics;
import ru.fmtk.hlystov.examinationapp.services.auth.UserAuthentification;
import ru.fmtk.hlystov.examinationapp.services.auth.UserCredential;
import ru.fmtk.hlystov.examinationapp.services.presenter.Presenter;

import java.util.Optional;

@ShellComponent("Examinator")
@Profile("Shell")
public class SellExaminator implements Examinator {
    private Presenter presenter;
    private Exam exam;
    private ExamStatistics statistics;
    private UserAuthentification userAuthentification;
    private User user;

    @Autowired
    public void setUserAuthentification(UserAuthentification userAuthentification) {
        this.userAuthentification = userAuthentification;
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

    @Override
    public void performExam() {
        System.out.println("Sell examinator: did an exam.");
        presenter.showGreetengs();
//        Optional<User> optUser = presenter.getUser();
//        presenter.showExamStart();
//        optUser.ifPresentOrElse(
//                user -> {
//                    askQuestions();
//                    presenter.showStatistics(statistics);
//                    presenter.showExamResult(exam.getNumberToSuccess() <= statistics.getRightQuestions());
//                },
//                presenter::showUserNeeded);
//        presenter.showGoodBy();
    }

    @Override
    public ExamStatistics getStatistics() {
        return statistics;
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @ShellMethod(value = "Login as a student.", key = "login")
    public String login(String firstName, String secondName) {
        if(firstName != null && secondName != null) {
            Optional<User> userOpt = userAuthentification.getUser(new UserCredential(firstName, secondName));
            if(userOpt.isPresent()) {
                return "You login as " + user.toString();
            }
        }
        return "Wrong user login!";
    }
}
