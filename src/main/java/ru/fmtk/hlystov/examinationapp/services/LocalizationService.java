package ru.fmtk.hlystov.examinationapp.services;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizationService {
    @NotNull
    private static final String STRINGS_RESOURCE_BUNDLE_NAME = "strings";
    @NotNull
    private final ResourceBundle strings;

    public LocalizationService() {
        this(Locale.getDefault());
    }

    public LocalizationService(Locale locale) {
        this.strings = ResourceBundle.getBundle(STRINGS_RESOURCE_BUNDLE_NAME, locale);
    }

    @Nullable
    public String getString(@NotNull String stringName) {
        try {
            return strings.getString(stringName);
        } catch (MissingResourceException | ClassCastException ignoreToNull) {
        }
        return null;
    }
}
