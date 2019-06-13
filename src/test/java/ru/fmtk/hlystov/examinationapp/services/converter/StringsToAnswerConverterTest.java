package ru.fmtk.hlystov.examinationapp.services.converter;

import org.junit.Before;
import org.junit.Test;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.Answer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.NumericAnswer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.OptionsAnswer;
import ru.fmtk.hlystov.examinationapp.domain.examination.answer.SingleAnswer;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.NumericQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.OptionsQuestion;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.SingleQuestion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringsToAnswerConverterTest {
    private StringsToAnswerConverter converter;

    @Before
    public void initTests() {
        converter = new StringsToAnswerConverter();
    }

    @Test
    public void convertNumericAnswer() {
        double number = 800.139;
        List<String> answers = Collections.singletonList(Double.toString(number));
        Optional<? extends Answer> answer = converter.convertAnswer(NumericQuestion.class, answers);
        assertTrue(answer.isPresent());
        assertTrue((new NumericAnswer(number)).isEquals(answer.get()));
    }

    @Test
    public void convertSingleAnswer() {
        int value = 1;
        List<String> answers = Arrays.asList(Integer.toString(value), "2", "3");
        Optional<? extends Answer> answer = converter.convertAnswer(SingleQuestion.class, answers);
        assertTrue(answer.isPresent());
        assertTrue(new SingleAnswer(value).isEquals(answer.get()));
    }

    @Test
    public void convertStraightOrderOptionsAnswer() {
        List<Integer> intAnswers = Arrays.asList(1, 2, 3, 4);
        List<String> answers = Arrays.asList("1", "2", "3", "4");
        Optional<? extends Answer> answer = converter.convertAnswer(OptionsQuestion.class, answers);
        assertTrue(answer.isPresent());
        assertTrue(new OptionsAnswer(intAnswers).isEquals(answer.get()));
    }

    @Test
    public void convertRandomOrderOptionsAnswer() {
        List<Integer> intAnswers = Arrays.asList(2, 1, 3);
        List<String> answers = Arrays.asList("3", "2", "1");
        Optional<? extends Answer> answer = converter.convertAnswer(OptionsQuestion.class, answers);
        assertTrue(answer.isPresent());
        assertTrue(new OptionsAnswer(intAnswers).isEquals(answer.get()));
    }

    @Test
    public void convertRepetedOptionsAnswer() {
        List<Integer> intAnswers = Arrays.asList(1, 2, 1);
        List<String> answers = Arrays.asList("2", "1", "2");
        Optional<? extends Answer> answer = converter.convertAnswer(OptionsQuestion.class, answers);
        assertTrue(answer.isPresent());
        assertTrue(new OptionsAnswer(intAnswers).isEquals(answer.get()));
    }

    @Test
    public void convertWrondOptionsAnswer() {
        List<Integer> intAnswers = Arrays.asList(1, 2, 3, 4);
        List<String> answers = Arrays.asList("5", "2", "3", "4");
        Optional<? extends Answer> answer = converter.convertAnswer(OptionsQuestion.class, answers);
        assertTrue(answer.isPresent());
        assertFalse(new OptionsAnswer(intAnswers).isEquals(answer.get()));
    }

}