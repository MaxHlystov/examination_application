package ru.fmtk.hlystov.examinationapp.services;


import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.Application;

import java.io.InputStream;
import java.util.Locale;

@Service
public class AppConfig {
    @NotNull
    private final String baseCSVResourceName;
    @NotNull
    private Locale locale;
    @NotNull
    private final MessageSource messageSource;

    public AppConfig(@Value("#{ systemProperties['user.language'] + '_' + systemProperties['user.country'] }")
                     @NotNull Locale locale,
                     @Value("${csv.path}") @NotNull String baseCSVResourceName,
                     @NotNull MessageSource messageSource) {
        this.locale = locale;
        this.baseCSVResourceName = baseCSVResourceName;
        this.messageSource = messageSource;
    }

    @NotNull
    public String getCSVResourceName() {
        String localeString = locale.toString();
        String withoutExt = FilenameUtils.removeExtension(baseCSVResourceName);
        String extension = FilenameUtils.getExtension(baseCSVResourceName);
        return String.format("%s_%s.%s", withoutExt, localeString, extension);
    }

    @Nullable
    public InputStream getSCVQuestionsStream() {
        return getLocalResourceStream(baseCSVResourceName,
                getCSVResourceName());
    }

    @Nullable
    public String getMessage(@NotNull String code, @Nullable String[] args) {
        return messageSource.getMessage(code, args, locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Nullable
    public static InputStream getLocalResourceStream(@NotNull String baseResourceName,
                                                     @NotNull String localResourceName) {
        InputStream in = Application.class.getResourceAsStream(localResourceName);
        if (in != null) {
            return in;
        }
        return Application.class.getResourceAsStream(baseResourceName);
    }
}
