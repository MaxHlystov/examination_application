package ru.fmtk.hlystov.examinationapp.services.examinator;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;


@Component
public class ShellPromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("exam:>",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}