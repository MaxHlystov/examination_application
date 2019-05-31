package ru.fmtk.hlystov.hw_examination_app.domain.examination;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import ru.fmtk.hlystov.hw_examination_app.Main;
import ru.fmtk.hlystov.hw_examination_app.domain.examination.question.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExamCSVFactory {
    @NonNull
    private final String resourceName;

    public ExamCSVFactory(@NonNull String resourceName) {
        this.resourceName = resourceName;
    }

    @Nullable
    public IExam createExam() {
        Exam exam = new Exam();
        try {
            fillFromResourceFile(exam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exam;
    }

    private void fillFromResourceFile(@NonNull Exam exam) throws IOException {
        InputStream in = Main.class.getResourceAsStream(resourceName);
        if (in != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                IQuestion question = parseQuestion(line);
                if (question != null) {
                    exam.addQuestion(question);
                }
            }
            csvReader.close();
            reader.close();
        }
    }

    @Nullable
    private IQuestion parseQuestion(@NonNull String[] line) {
        try {
            QuestionType type = QuestionType.valueOf(line[0]);
            List<String> options = getValuableSubarray(line, 2, 6);
            List<String> answers = getValuableSubarray(line, 7, 11);
            if (answers != null && answers.size() > 0) {
                switch (type) {
                    case NUMBER_QUESTION:
                        return new NumberQuestion(line[1], options, answers);
                    case OPTIONS_QUESTION:
                        if (options.size() > 0) {
                            return new OptionsQuestion(line[1], options, answers);
                        }
                        break;
                    case SINGLE_QUESTION:
                        if (options.size() > 0) {
                            return new SingleQuestion(line[1], options, answers);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (IllegalArgumentException | NullPointerException ignored) {
        }
        return null;
    }

    @NonNull
    private static List<String> getValuableSubarray(@NonNull String[] array, int from, int to) {
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
