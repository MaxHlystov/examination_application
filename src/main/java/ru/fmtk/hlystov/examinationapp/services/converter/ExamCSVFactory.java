package ru.fmtk.hlystov.examinationapp.services.converter;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import ru.fmtk.hlystov.examinationapp.Main;
import ru.fmtk.hlystov.examinationapp.domain.examination.Exam;
import ru.fmtk.hlystov.examinationapp.domain.examination.ExamImpl;
import ru.fmtk.hlystov.examinationapp.domain.examination.question.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExamCSVFactory {
    @NotNull
    private final String resourceName;
    @NotNull
    StringsToQuestionsConverter questionConverter;

    public ExamCSVFactory(@NotNull String resourceName,
                          @NotNull StringsToQuestionsConverter questionConverter) {
        this.resourceName = resourceName;
        this.questionConverter = questionConverter;
    }

    @NotNull
    public Exam createExam() {
        ExamImpl exam = new ExamImpl();
        try {
            fillFromResourceFile(exam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exam;
    }

    private void fillFromResourceFile(@NotNull ExamImpl exam) throws IOException {
        InputStream in = Main.class.getResourceAsStream(resourceName);
        if (in != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                String type = line[0];
                String title = line[1];
                List<String> options = getValuableSubarray(line, 2, 6);
                List<String> answers = getValuableSubarray(line, 7, 11);
                if (!StringUtils.isEmpty(type) && !StringUtils.isEmpty(title) && answers.size() > 0) {
                    Question question = questionConverter.convertQuestion(type, title, options, answers);
                    if (question != null) {
                        exam.addQuestion(question);
                    }
                }
            }
            csvReader.close();
            reader.close();
        }
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
