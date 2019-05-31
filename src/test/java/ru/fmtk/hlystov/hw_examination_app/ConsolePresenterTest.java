package ru.fmtk.hlystov.hw_examination_app;

import org.junit.Test;
import ru.fmtk.hlystov.hw_examination_app.domain.IUser;
import ru.fmtk.hlystov.hw_examination_app.services.auth.ConsoleUserAuth;
import ru.fmtk.hlystov.hw_examination_app.services.presenter.ConsolePresenter;

import static org.junit.Assert.assertNotNull;

public class ConsolePresenterTest {

    @Test
    public void getUserNonNull() {
        ConsoleUserAuth userAuth = new ConsoleUserAuth();
        ConsolePresenter cp = new ConsolePresenter(userAuth);
        IUser u = cp.getUser();
        assertNotNull(u);
    }
}