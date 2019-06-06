package ru.fmtk.hlystov.examinationapp;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
public class TestConsole {
    private final PipedOutputStream outToInput;
    private final ByteArrayOutputStream outputBytes;
    private final InputStream inputStream;
    private final PrintStream printStream;

    public TestConsole() throws IOException {
        outputBytes = new ByteArrayOutputStream();
        printStream = new PrintStream(outputBytes);
        outToInput = new PipedOutputStream();
        inputStream = new PipedInputStream(outToInput);
    }

    public void clearScreen() {
        printStream.flush();
        outputBytes.reset();
    }

    public Scanner getCurrentScreenScanner() {
        printStream.flush();
        return new Scanner(new ByteArrayInputStream(outputBytes.toByteArray()));
    }

    public void printlnToStdin(String text) throws IOException {
        outToInput.write((text + '\n').getBytes());
        outToInput.flush();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }


}
