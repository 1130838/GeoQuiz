package com.brunodevesa.geoquiz.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brunodevesa on 01/03/16.
 */
public class Player {
    int pontuation;
    ArrayList<Answer> answers;
    int numberQuestions;
    private int mNumberValidAnswers;

    public Player(int numberQuestions) {
        this.answers = new ArrayList<>(numberQuestions);
        this.pontuation = 0;
        this.numberQuestions = numberQuestions;
        initializeAnswers(numberQuestions);
    }

    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }

    public void setPlayerAnswer(int currentIndex, Boolean answer) {
        answers.get(currentIndex).setAnswer(answer);
    }


    public int getPontuation() {
        return pontuation;
    }

    public void setPontuation(int pontuation) {
        this.pontuation = pontuation;
    }

    public void initializeAnswers(int numberQuestions) {
        for (int i = 0; i < numberQuestions; i++) {
            this.answers.add(new Answer(null, false));
        }
    }

    public void addAnswer(int currentIndex, boolean b) {
        getAnswers().get(currentIndex).setAnswer(b);

    }

    public int getNumberValidAnswers() {
        int numberQuestionsAnswered = 0;
        for (int i = 0; i < numberQuestions; i++) {
            if (this.answers.get(i).getIsAnswered()){
                numberQuestionsAnswered++;
            }
        }
        return numberQuestionsAnswered;
    }
}
