package ru.fmtk.hlystov.examinationapp;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import ru.fmtk.hlystov.examinationapp.domain.User;
import ru.fmtk.hlystov.examinationapp.domain.UserImpl;
import ru.fmtk.hlystov.examinationapp.services.auth.UserAuthentification;
import ru.fmtk.hlystov.examinationapp.services.converter.StringsToAnswerConverter;
import ru.fmtk.hlystov.examinationapp.services.presenter.ConsolePresenter;

import static org.junit.Assert.assertEquals;

public class ConsolePresenterTest {

    @Test
    public void getUserNonNull() {
        User user = new UserImpl("a", "b");
        UserAuthStub userAuth = new UserAuthStub(user);
        StringsToAnswerConverter answerConverter = new StringsToAnswerConverter();
        ConsolePresenter cp = new ConsolePresenter(userAuth, answerConverter);
        User gotUser = cp.getUser();
        assertEquals(user, gotUser);
    }

    public class UserAuthStub implements UserAuthentification {
        @NotNull
        User user;

        public UserAuthStub(@NotNull User user) {
            this.user = user;
        }

        @Override
        @NotNull
        public User getUser() {
            return user;
        }
    }
}