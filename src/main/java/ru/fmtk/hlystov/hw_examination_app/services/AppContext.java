package ru.fmtk.hlystov.hw_examination_app.services;


import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class AppContext {
    @NonNull
    private static final String STRINGS_RESOURCE_BUNDLE_NAME = "strings";
    @NonNull
    private final ResourceBundle strings;

    public AppContext() { this(Locale.getDefault()); }

    public AppContext(Locale locale) {
        this.strings = ResourceBundle.getBundle(STRINGS_RESOURCE_BUNDLE_NAME, locale);
    }

    @Nullable
    public String getString(@NonNull String stringName) {
        try { return strings.getString(stringName); }
        catch (MissingResourceException | ClassCastException ignoreToNull) { }
        return null;
    }
}
