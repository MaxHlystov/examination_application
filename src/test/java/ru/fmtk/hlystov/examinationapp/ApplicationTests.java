package ru.fmtk.hlystov.examinationapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private TestConsole testConsole;

    @Test
    public void contextLoads() {
    }

    @Autowired
    public void setTestConsole(TestConsole testConsole) {
        this.testConsole = testConsole;
    }

    public TestConsole getTestConsole() {
        return testConsole;
    }

    @Bean
    public InputStream getConsoleInputStream() {
        return testConsole.getInputStream();
    }

    @Bean
    public PrintStream getConsoleOutStream() {
        return testConsole.getPrintStream();
    }
}
