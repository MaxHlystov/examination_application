package ru.fmtk.hlystov.hw_examination_app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        IQuestion q = context.getBean(Question.class);
        System.out.println(q.getCount());
    }

}
