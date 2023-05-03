package com.asem.onlinequiz;

public class Student {
    String name, score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Student(String name, String score) {
        this.name = name;
        this.score = score;
    }
}
