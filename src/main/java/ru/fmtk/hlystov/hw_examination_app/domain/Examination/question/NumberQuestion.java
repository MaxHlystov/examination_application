package ru.fmtk.hlystov.hw_examination_app.domain.examination.question;

import org.springframework.lang.NonNull;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.NumberAnswer;

import java.util.List;

public class NumberQuestion extends BaseQuestion {
    public NumberQuestion(@NonNull String title, @NonNull List<String> options, List<String> answers) {
        super(title, options);
        setRightAnswer(stringsToAnswer(answers));
    }

    @Override
    @NonNull
    public IAnswer stringsToAnswer(List<String> answers) {
        try {
            return new NumberAnswer(Float.parseFloat(answers.get(0)));
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }
        return null;
    }
}
