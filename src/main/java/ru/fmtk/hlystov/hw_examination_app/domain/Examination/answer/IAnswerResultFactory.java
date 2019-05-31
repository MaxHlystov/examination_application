package ru.fmtk.hlystov.hw_examination_app.domain.examination.answer;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface IAnswerResultFactory {
    @NonNull
    IAnswerResult checkResult(@Nullable IAnswer rightAnswer, @Nullable IAnswer checkedAnswer);
}
