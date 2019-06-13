package ru.fmtk.hlystov.examinationapp.services.converter;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionsCSVLoader {
    @NotNull
    private final StringsToQuestionsConverter questionConverter;

    public QuestionsCSVLoader(@NotNull StringsToQuestionsConverter questionConverter) {
        this.questionConverter = questionConverter;
    }

    @NotNull
    public List<Question> readQuestions(@Nullable InputStream inputStream) throws IOException {
        List<Question> questions = new ArrayList<>();
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                Question question = parceLine(line);
                if (question != null) {
                    questions.add(question);
                }
            }
            csvReader.close();
            reader.close();
        }
        return questions;
    }

    @Nullable
    public Question parceLine(@NotNull String[] line) {
        String type = line[0];
        String title = line[1];
        List<String> options = getValuableSubarray(line, 2, 6);
        List<String> answers = getValuableSubarray(line, 7, 11);
        if (!StringUtils.isEmpty(type) && !StringUtils.isEmpty(title) && answers.size() > 0) {
            return questionConverter.convertQuestion(type, title, options, answers);
        }
        return null;
    }

    @NotNull
    private static List<String> getValuableSubarray(@NotNull String[] array, int from, int to) {
        if (from > to || from < 0) {
            return new ArrayList<>();
        }
        return Arrays.stream(array)
                .skip(from)
                .limit(to - from + 1)
                .filter(s -> !StringUtils.isEmpty(s))
                .collect(Collectors.toList());
    }
}
