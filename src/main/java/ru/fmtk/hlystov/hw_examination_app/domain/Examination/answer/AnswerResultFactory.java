package ru.fmtk.hlystov.hw_examination_app.domain.examination.answer;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.fmtk.hlystov.hw_examination_app.Main;
import ru.fmtk.hlystov.hw_examination_app.services.AppContext;

public class AnswerResultFactory implements IAnswerResultFactory {
    @Override
    @NonNull
    public IAnswerResult checkResult(@Nullable IAnswer rightAnswer, @Nullable IAnswer checkedAnswer) {
        AppContext appContext = Main.getAppContext();
        StringBuilder sb = new StringBuilder();
        boolean equals = (rightAnswer != null) && rightAnswer.equals(checkedAnswer);
        if (equals) {
            sb.append(appContext.getString("answerResultFactory__ok"));
        } else {
            sb.append(appContext.getString("answerResultFactory__wrong"));
        }
        return new AnswerResult(sb.toString(), equals);
    }
}
