package com.panos.lenovoppc.lighterapp;

/**
 * Created by Lenovo pPc on 26-Jan-18.
 */

public class QuestionModel {

    private String question;
    private String category;
    private boolean censored;

    public QuestionModel(String question, String category, boolean censored) {
        this.question = question;
        this.category = category;
        this.censored = censored;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCensored() {
        return censored;
    }

    public void setCensored(boolean censored) {
        this.censored = censored;
    }
}
