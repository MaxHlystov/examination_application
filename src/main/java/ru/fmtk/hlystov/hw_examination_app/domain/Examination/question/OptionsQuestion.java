package ru.fmtk.hlystov.hw_examination_app.domain.examination.question;

import org.springframework.lang.NonNull;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.OptionsAnswer;

import java.util.List;
import java.util.stream.Collectors;

public class OptionsQuestion extends BaseQuestion {
    public OptionsQuestion(@NonNull String title, @NonNull List<String> options, @NonNull List<String> answers) {
        super(title, options);
        setRightAnswer(stringsToAnswer(answers));
    }

    @Override
    @NonNull
    public IAnswer stringsToAnswer(@NonNull List<String> answers) {
        return new OptionsAnswer(answers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }
}
