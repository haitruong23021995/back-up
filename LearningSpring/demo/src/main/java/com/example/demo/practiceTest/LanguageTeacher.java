package com.example.demo.practiceTest;

import java.util.ArrayList;
import java.util.List;

class LanguageStudent {
    private List<String> languages = new ArrayList<>();

    public List<String> getLanguages() {
        return languages;
    }

    public void addLanguage(String language) {
        languages.add(language);
    }
}

public class LanguageTeacher extends LanguageStudent {

    public boolean teach(LanguageStudent student, String language) {
        boolean teachSuccessful = super.getLanguages().contains(language);
        if (teachSuccessful) {
            student.addLanguage(language);
        }
        return teachSuccessful;
    }

    public static void main(String[] args) {
        LanguageTeacher teacher = new LanguageTeacher();
        teacher.addLanguage("English");

        LanguageStudent student = new LanguageStudent();
        System.out.println(teacher.teach(student, "English"));

        for (String language : student.getLanguages())
            System.out.println(language);
    }
}
