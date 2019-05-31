package ru.fmtk.hlystov.hw_examination_app.domain.examination.question;

import org.springframework.lang.NonNull;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.IAnswer;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.answer.SingleAnswer;

import java.util.List;

public class SingleQuestion extends BaseQuestion {
    public SingleQuestion(@NonNull String title, @NonNull List<String> options, @NonNull List<String> answer) {
        super(title, options);
        setRightAnswer(stringsToAnswer(answer));
    }

    @Override
    @NonNull
    public IAnswer stringsToAnswer(@NonNull List<String> answers) {
        return new SingleAnswer(Integer.parseInt(answers.get(0)));
    }
}
