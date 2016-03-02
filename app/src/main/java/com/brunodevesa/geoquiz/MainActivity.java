package com.brunodevesa.geoquiz;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.BoringLayout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.brunodevesa.geoquiz.model.Answer;
import com.brunodevesa.geoquiz.model.Player;
import com.brunodevesa.geoquiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView m_content_main_tv_question, m_content_main_tv_format_questionCounting,
            m_content_main_tv_finalResult;
    private Button m_content_main_btn_true, m_content_main_btn_false,
            m_content_main_btn_previousQuestion, m_content_main_btn_nextQuestion;
    private List<Question> questionList = new ArrayList<>();
    private int currentIndex = 0;
    private int totalQuestions;
    private Player player;

    public void initializeQuestionList(List<Question> questionList) {
        Question q1 = new Question(R.string.question_1, true);
        Question q2 = new Question(R.string.question_2, false);
        Question q3 = new Question(R.string.question_3, true);
        Question q4 = new Question(R.string.question_4, false);
        Question q5 = new Question(R.string.question_5, true);

        questionList.add(q1);
        questionList.add(q2);
        questionList.add(q3);
        questionList.add(q4);
        questionList.add(q5);

        String questionCountingFormat = getResources().getString(R.string.questionCounting);
        String questionCountingMsg = String.format(questionCountingFormat, currentIndex + 1, questionList.size());
        m_content_main_tv_format_questionCounting.setText(questionCountingMsg);

        totalQuestions = questionList.size();

        player = new Player(totalQuestions);

        m_content_main_tv_finalResult.setText("");

        m_content_main_tv_question.setText(questionList.get(currentIndex).getQuestionID());

    }

    private boolean checkAnswer(boolean answer) {
        if (questionList.get(currentIndex).isCorrectAnswer() == answer) {

            Toast.makeText(MainActivity.this, "Correct !", Toast.LENGTH_LONG).show();

         /*   if (!player.getAnswers().get(currentIndex).getIsAnswered()) {*/

                player.setPontuation(player.getPontuation() + 1);
                player.setPlayerAnswer(currentIndex, true);
          /*  }*/


            return true;

        } else {
            Toast.makeText(MainActivity.this, "Incorrect..", Toast.LENGTH_LONG).show();
            player.setPlayerAnswer(currentIndex, false);
            return false;
        }
    }

    private void initializeButtons() {
        m_content_main_btn_true.setBackgroundColor(getResources().getColor(R.color.btn_color_normal));
        m_content_main_btn_true.setEnabled(true);
        m_content_main_btn_false.setBackgroundColor(getResources().getColor(R.color.btn_color_normal));
        m_content_main_btn_false.setEnabled(true);
    }

    private boolean isGameOver() {
        if (player.getNumberValidAnswers() == totalQuestions) {
           // m_content_main_btn_nextQuestion.setEnabled(false);
           // m_content_main_btn_previousQuestion.setEnabled(false);
            return true;
        } else {
            return false;
        }
    }


    private void updateButtons(boolean correctAnswer, boolean playerAnswer) {
        if (correctAnswer == true && playerAnswer == true) {
            m_content_main_btn_true.setBackgroundColor(Color.GREEN);
        }
        if (correctAnswer == true && playerAnswer == false) {
            m_content_main_btn_false.setBackgroundColor(Color.RED);
        }
        if (correctAnswer == false && playerAnswer == true) {
            m_content_main_btn_false.setBackgroundColor(Color.GREEN);
        }
        if (correctAnswer == false && playerAnswer == false) {
            m_content_main_btn_false.setBackgroundColor(Color.RED);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_content_main_tv_question = (TextView) findViewById(R.id.content_main_tv_question);
        m_content_main_tv_finalResult = (TextView) findViewById(R.id.content_main_tv_finalResult);

        m_content_main_tv_format_questionCounting = (TextView) findViewById(R.id.content_main_tv_questionCounting);

        m_content_main_btn_true = (Button) findViewById(R.id.content_main_btn_true);
        m_content_main_btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.setPlayerAnswer(currentIndex, true);

                player.getAnswers().get(currentIndex).setChosenButton(true);

                if (checkAnswer(true)) {

                    m_content_main_btn_true.setBackgroundColor(Color.GREEN);

                } else {
                    m_content_main_btn_true.setBackgroundColor(Color.RED);
                }

                String partialResultTextFormat = getResources().getString(R.string.finalResult);
                String partialResultTextMsg = String.format(partialResultTextFormat,
                        player.getNumberValidAnswers(), player.getPontuation(), questionList.size());
                m_content_main_tv_finalResult.setText(partialResultTextMsg);

                m_content_main_btn_true.setEnabled(false);
                m_content_main_btn_false.setEnabled(false);


                // updateLayout(true);

                if (isGameOver()) {

                    String finalResultTextFormat = getResources().getString(R.string.finalResult);
                    String finalResultTextMsg = String.format(finalResultTextFormat,
                            player.getNumberValidAnswers(), player.getPontuation(), questionList.size());
                    m_content_main_tv_finalResult.setText(finalResultTextMsg);
                }

            }
        });


        m_content_main_btn_false = (Button) findViewById(R.id.content_main_tv_btn_false);
        m_content_main_btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.setPlayerAnswer(currentIndex, false);

                player.getAnswers().get(currentIndex).setChosenButton(false);

                if (checkAnswer(false)) {

                    m_content_main_btn_false.setBackgroundColor(Color.GREEN);

                } else {
                    m_content_main_btn_false.setBackgroundColor(Color.RED);
                }

                // updateLayout(false);

                if (isGameOver()) {

                    String finalResultTextFormat = getResources().getString(R.string.finalResult);
                    String finalResultTextMsg = String.format(finalResultTextFormat,
                            player.getNumberValidAnswers(), player.getPontuation(), questionList.size());
                    m_content_main_tv_finalResult.setText(finalResultTextMsg);
                }

            }
        });

        m_content_main_btn_previousQuestion = (Button) findViewById(R.id.content_main_btn_previousQuestion);
        m_content_main_btn_previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((currentIndex > 0)) {

                    currentIndex--;

                    if (questionNotAnswered()) {

                        initializeButtons();
                        String questionCountingFormat = getResources().getString(R.string.questionCounting);
                        String questionCountingMsg = String.format(questionCountingFormat, currentIndex + 1, questionList.size());
                        m_content_main_tv_format_questionCounting.setText(questionCountingMsg);

                        int question = questionList.get(currentIndex).getQuestionID();
                        m_content_main_tv_question.setText((question));

                        boolean playerAnswer = player.getAnswers().get(currentIndex).getAnswer();

                        if (checkAnswer(playerAnswer)) {
                            boolean choseButton = player.getAnswers().get(currentIndex).getChosenButton();
                            if (choseButton == true) {
                                m_content_main_btn_true.setBackgroundColor(Color.GREEN);
                            } else {
                                m_content_main_btn_false.setBackgroundColor(Color.GREEN);
                            }

                        } else {
                            m_content_main_btn_true.setBackgroundColor(Color.RED);
                        }

                        String partialResults = String.valueOf("answered: " + player.getNumberValidAnswers()) + " / Total: " + String.valueOf(totalQuestions);
                        m_content_main_tv_finalResult.setText(partialResults);

                    } else {

                        initializeButtons();
                        updateLayout();

                    }

                }

            }
        });

        m_content_main_btn_nextQuestion = (Button) findViewById(R.id.content_main_btn_nextQuestion);


        initializeQuestionList(this.questionList);

        m_content_main_btn_nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((currentIndex < questionList.size() - 1)) {

                    currentIndex++;

                    if (questionNotAnswered()) {

                        initializeButtons();
                        String questionCountingFormat = getResources().getString(R.string.questionCounting);
                        String questionCountingMsg = String.format(questionCountingFormat, currentIndex + 1, questionList.size());
                        m_content_main_tv_format_questionCounting.setText(questionCountingMsg);

                        int question = questionList.get(currentIndex).getQuestionID();
                        m_content_main_tv_question.setText((question));

                        String partialResults = String.valueOf("answered: " + player.getNumberValidAnswers()) + " / Total: " + String.valueOf(totalQuestions);
                        m_content_main_tv_finalResult.setText(partialResults);
                    } else {

                        initializeButtons();
                        updateLayout();
                    }
                }
            }
        });

        //  m_content_main_tv_question.setText(questionList.get(currentIndex).getQuestionID());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void updateLayout() {
        String questionCountingFormat = getResources().getString(R.string.questionCounting);
        String questionCountingMsg = String.format(questionCountingFormat, currentIndex + 1, questionList.size());
        m_content_main_tv_format_questionCounting.setText(questionCountingMsg);

        int question = questionList.get(currentIndex).getQuestionID();
        m_content_main_tv_question.setText((question));

        boolean playerAnswer = player.getAnswers().get(currentIndex).getAnswer();
        boolean correctAnswer = questionList.get(currentIndex).isCorrectAnswer();

        updateButtons(correctAnswer, playerAnswer);

        m_content_main_btn_true.setEnabled(false);
        m_content_main_btn_false.setEnabled(false);

        String partialResults = String.valueOf("question already answered!  questions answered = " + player.getNumberValidAnswers()) + " / Total: " + String.valueOf(totalQuestions);
        m_content_main_tv_finalResult.setText(partialResults);
    }


    private boolean questionNotAnswered() {

        ArrayList<Answer> playersAnswer = player.getAnswers();
        Boolean check = playersAnswer.get(currentIndex).getIsAnswered();
        if (check == false) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
