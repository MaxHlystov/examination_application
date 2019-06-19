package ru.fmtk.hlystov.examinationapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fmtk.hlystov.examinationapp.services.examinator.Examinator;
import ru.fmtk.hlystov.examinationapp.services.examinator.ExaminatorImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private TestConsole testConsole;

    @Test
    public void contextLoads() throws IOException {
        testConsole.printlnToStdin("First");
        testConsole.printlnToStdin("Second");
        testConsole.printlnToStdin("10");
        testConsole.printlnToStdin("1 2");
        testConsole.printlnToStdin("5");
        testConsole.printlnToStdin("6");
        testConsole.printlnToStdin("2");
    }

    @Autowired
    public void setTestConsole(TestConsole testConsole) {
        this.testConsole = testConsole;
    }

    public TestConsole getTestConsole() {
        return testConsole;
    }

    @Configuration
    public static class TestBeens {
        private TestConsole testConsole;


        @Bean
        public InputStream getConsoleInputStream() {
            return testConsole.getInputStream();
        }

        @Bean
        @Primary
        public PrintStream getConsoleOutStream() {
            return testConsole.getPrintStream();
        }

        @Bean
        public TestConsole getTestConsole() throws IOException {
            return new TestConsole();
        }

        @Autowired
        public void setTestConsole(TestConsole testConsole) {
            this.testConsole = testConsole;
        }
    }
}
