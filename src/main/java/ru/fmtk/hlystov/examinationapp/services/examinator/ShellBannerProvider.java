package ru.fmtk.hlystov.examinationapp.services.examinator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShellBannerProvider extends DefaultBannerProvider {
    private final String version;

    public ShellBannerProvider(@Value("${application.version}") String version) {
        this.version = version;
    }

    public String getBanner() {
        StringBuilder buf = new StringBuilder();
        buf.append("=======================================").append(OsUtils.LINE_SEPARATOR);
        buf.append("*            Examination              *").append(OsUtils.LINE_SEPARATOR);
        buf.append("=======================================").append(OsUtils.LINE_SEPARATOR);
        buf.append("Version:").append(version);
        return buf.toString();
    }

    public String getVersion() {
        return version;
    }

    public String getWelcomeMessage() {
        return "Welcome to examination app";
    }

    public String getProviderName() {
        return "Shell Examination Banner";
    }
}