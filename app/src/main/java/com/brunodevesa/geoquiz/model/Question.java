package com.brunodevesa.geoquiz.model;

/**
 * Created by brunodevesa on 01/03/16.
 */
public class Question {

    private int mQuestionID;
    private boolean mCorrectAnswer;

    public Question(int questionID, boolean correctAnswer) {
        mQuestionID = questionID;
        mCorrectAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer() {
        return mCorrectAnswer;
    }


    public void setCorrectAnswer(boolean correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }
}
