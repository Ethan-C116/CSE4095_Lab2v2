package com.example.lab2_v2.Model;

public class Question {
    private final String question;
    private final boolean isTrue;


    public Question(String questionText, boolean answer){
        this.question = questionText;
        this.isTrue = answer;
    }

    public boolean getAnswer(){
        return this.isTrue;
    };

    public String getString(){
        return this.question;
    }
}
