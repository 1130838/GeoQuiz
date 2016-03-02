package com.brunodevesa.geoquiz.model;

/**
 * Created by brunodevesa on 02/03/16.
 */
public class Answer {


    private Boolean answer;
    private Boolean isAnswered;

    public Boolean getChosenButton() {
        return chosenButton;
    }

    public void setChosenButton(Boolean chosenButton) {
        this.chosenButton = chosenButton;
    }

    private Boolean chosenButton;

    public Answer( Boolean answer, Boolean isAnswered) {
        this.answer = answer;
        this.isAnswered = isAnswered;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
        setIsAnswered();
    }

    public Boolean getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered() {
        this.isAnswered = true;
    }

}
