package ru.fmtk.hlystov.examinationapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(Application.class)
@PropertySource("/properties")
public class TestConfiguration {
}
