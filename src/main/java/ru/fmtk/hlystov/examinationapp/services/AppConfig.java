package ru.fmtk.hlystov.examinationapp.services;


import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.fmtk.hlystov.examinationapp.Application;

import java.io.InputStream;
import java.util.Locale;
import java.util.Optional;

@Service
@ConfigurationProperties("app-config")
public class AppConfig {
    @NotNull
    private String baseCSVResourceName;
    @NotNull
    private Locale locale;
    @NotNull
    private String version;
    @NotNull
    private final MessageSource messageSource;
    private int rightAnswersToSuccess;

    public AppConfig(@Value("#{ systemProperties['user.language'] + '_' + systemProperties['user.country'] }")
                     @NotNull Locale locale,
                     @NotNull MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    @NotNull
    public String getCSVResourceName() {
        String localeString = locale.toString();
        String withoutExt = FilenameUtils.removeExtension(baseCSVResourceName);
        String extension = FilenameUtils.getExtension(baseCSVResourceName);
        return String.format("%s_%s.%s", withoutExt, localeString, extension);
    }

    @NotNull
    public Optional<InputStream> getSCVQuestionsStream() {
        return getLocalResourceStream(baseCSVResourceName, getCSVResourceName());
    }

    @NotNull
    public String getMessage(@NotNull String code, @Nullable String[] args) {
        var msg = messageSource.getMessage(code, args, locale);
        return (msg == null) ? "" : msg;
    }

    @NotNull
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(@NotNull Locale locale) {
        this.locale = locale;
    }

    @NotNull
    public static Optional<InputStream> getLocalResourceStream(@NotNull String baseResourceName,
                                                               @NotNull String localResourceName) {
        InputStream in = Application.class.getResourceAsStream(localResourceName);
        if (in != null) {
            return Optional.of(in);
        }
        return Optional.ofNullable(Application.class.getResourceAsStream(baseResourceName));
    }

    public void setBaseCSVResourceName(@NotNull String baseCSVResourceName) {
        this.baseCSVResourceName = baseCSVResourceName;
    }

    public int getRightAnswersToSuccess() {
        return rightAnswersToSuccess;
    }

    public void setRightAnswersToSuccess(int rightAnswersToSuccess) {
        this.rightAnswersToSuccess = rightAnswersToSuccess;
    }
}
