package com.example.android.inventionquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    public static final String SAVE_SCORE = "scoreMessage";
    public static final String SAVE_NAME = "save_name";
    static final String STATE_SCORE = "totalScore";

    int totalScore;
    Chronometer chronometer;
    private TextView mDisplayText;
    private String name;

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mDisplayText = (TextView) findViewById(R.id.usernameTextView);
        //TextView name = mDisplayText;

        // Pass an Intent of the name
        Intent intentThatStartedThisActvity = getIntent();
        if (intentThatStartedThisActvity.hasExtra(Intent.EXTRA_TEXT)) {
            String username = intentThatStartedThisActvity.getStringExtra(Intent.EXTRA_TEXT);
            mDisplayText.setText(username);
        }

        // Start of Chronometer
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.start();
    }


    // Method displays the given text on the screen.
    // Method for calculating +  displaying the Results after pressing Submit button
    public void submitResults(View view) {
        totalScore = 0;

        //Evaluation of Question 1 - all correct
        CheckBox answerToQ1a = (CheckBox) findViewById(R.id.checkBox_1a);
        boolean answer1a = answerToQ1a.isChecked();
        CheckBox answerToQ1b = (CheckBox) findViewById(R.id.checkBox_1b);
        boolean answer1b = answerToQ1b.isChecked();
        CheckBox answerToQ1c = (CheckBox) findViewById(R.id.checkBox_1c);
        boolean answer1c = answerToQ1c.isChecked();
        CheckBox answerToQ1d = (CheckBox) findViewById(R.id.checkBox_1d);
        boolean answer1d = answerToQ1d.isChecked();
        if (answer1a && answer1b && answer1c && answer1d) {
            totalScore++;
        }

        //Evaluation of Question 2 - C correct
        RadioButton RadioQ2C = (RadioButton) findViewById(R.id.Q2_c);
        boolean answerQ2 = RadioQ2C.isChecked();
        if (answerQ2) {
            totalScore++;
        }

        //Evaluation of Question 3 - C correct
        RadioButton RadioQ3C = (RadioButton) findViewById(R.id.Q3_c);
        boolean answerQ3 = RadioQ3C.isChecked();
        if (answerQ3) {
            totalScore++;
        }

        //Evaluation of Question 4 - Edison correct
        RadioButton RadioQ4b = (RadioButton) findViewById(R.id.Q4_b);
        boolean answerQ4 = RadioQ4b.isChecked();
        if (answerQ4) {
            totalScore++;
        }

        //Evaluation of Question 5 - B correct
        RadioButton RadioQ5b = (RadioButton) findViewById(R.id.Q5_b);
        boolean answerQ5 = RadioQ5b.isChecked();
        if (answerQ5) {
            totalScore++;
        }

        //Evaluation of Question 6 - C correct
        RadioButton RadioQ6c = (RadioButton) findViewById(R.id.Q6_c);
        boolean answerQ6 = RadioQ6c.isChecked();
        if (answerQ6) {
            totalScore++;
        }

        //Evaluation of Question 7 - A correct
        RadioButton RadioQ7a = (RadioButton) findViewById(R.id.Q7_a);
        boolean answerQ7 = RadioQ7a.isChecked();
        if (answerQ7) {
            totalScore++;
        }

        //Evaluation of Question 8 -  Answer 53 correct
        EditText answerToQ8 = (EditText) findViewById(R.id.answer_Q8);
        String answerQ8 = answerToQ8.getText().toString();
        if (answerToQ8.getText().toString().equals("53")) {
            totalScore++;
        }

        //Getting name from username TextView
        TextView name = (TextView) findViewById(R.id.usernameTextView);
        name.getText();

        //Creating scoreMessage
        String scoreMessage = name.getText() + ", Your total score is " + totalScore + " out of 8 points!";

        // after pressing submit button appears toast informing that at least one question should be answered
        if (totalScore == 0) {
            Toast.makeText(this, "You must answer at least one question!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Choosing medal picture and text based on achieved score

        if (totalScore >= 6) {
            TextView congratulationMessage = (TextView) findViewById(R.id.result_textView2);
            congratulationMessage.setText(getString(R.string.scoredWell));
            ImageView imageView = (ImageView) findViewById(R.id.medal);
            imageView.setImageResource(R.drawable.medal_gold);
            imageView.setVisibility(View.VISIBLE);

        } else if (totalScore >= 4) {
            TextView congratulationMessage = (TextView) findViewById(R.id.result_textView2);
            congratulationMessage.setText(getString(R.string.scoredOk));
            ImageView imageView = (ImageView) findViewById(R.id.medal);
            imageView.setImageResource(R.drawable.medal_silver);
            imageView.setVisibility(View.VISIBLE);

        } else {
            TextView congratulationMessage = (TextView) findViewById(R.id.result_textView2);
            congratulationMessage.setText(getString(R.string.scoredBad));
            ImageView imageView = (ImageView) findViewById(R.id.medal);
            imageView.setImageResource(R.drawable.medal_bronze);
            imageView.setVisibility(View.VISIBLE);

        }

        // Displaying correct answers,  score message and toast
        String correctAnswers = getString(R.string.correct_answers);
        TextView correctAnswersMessage = (TextView) findViewById(R.id.correctAnswers_TextView);
        correctAnswersMessage.setText(correctAnswers);
        correctAnswersMessage.setVisibility(View.VISIBLE);

        displayMessage(scoreMessage);
        Toast.makeText(this, "Your quiz was submited. " + scoreMessage, Toast.LENGTH_LONG).show();

        //Chronometer stops when button is clicked
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.stop();
    }

    //Reset total score
    private void displayMessage(String message) {
        TextView scoreTextView1 = (TextView) findViewById(R.id.result_textView);
        scoreTextView1.setText(message);
        scoreTextView1.setVisibility(View.VISIBLE);
    }

    // Method to save the points value when the screen is rotated.
    public void reset(View view) {
        totalScore = 0;

        CheckBox Q1a1 = (CheckBox) findViewById(R.id.checkBox_1a);
        Q1a1.setChecked(false);
        CheckBox Q1a2 = (CheckBox) findViewById(R.id.checkBox_1b);
        Q1a2.setChecked(false);
        CheckBox Q1a3 = (CheckBox) findViewById(R.id.checkBox_1c);
        Q1a3.setChecked(false);
        CheckBox Q1a4 = (CheckBox) findViewById(R.id.checkBox_1d);
        Q1a4.setChecked(false);
        RadioButton Q2a1 = (RadioButton) findViewById(R.id.Q2_a);
        Q2a1.setChecked(false);
        RadioButton Q2a2 = (RadioButton) findViewById(R.id.Q2_b);
        Q2a2.setChecked(false);
        RadioButton Q2a3 = (RadioButton) findViewById(R.id.Q2_c);
        Q2a3.setChecked(false);

        RadioGroup Q3 = (RadioGroup) findViewById(R.id.Q3);
        Q3.clearCheck();

        RadioGroup Q4 = (RadioGroup) findViewById(R.id.Q4);
        Q4.clearCheck();

        RadioGroup Q5 = (RadioGroup) findViewById(R.id.Q5);
        Q5.clearCheck();

        RadioGroup Q6 = (RadioGroup) findViewById(R.id.Q6);
        Q6.clearCheck();

        RadioGroup Q7 = (RadioGroup) findViewById(R.id.Q7);
        Q7.clearCheck();

        EditText Q8 = (EditText) findViewById(R.id.answer_Q8);
        Q8.setText(getString(R.string.zero));

        finish();
        startActivity(new Intent(this, QuizActivity.class));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("STATE_SCORE", totalScore);
        savedInstanceState.putString("STATE_NAME", name);
        savedInstanceState.putLong("ChronoTime", chronometer.getBase());
        Log.v("OnSaveInstanceState", "Your points have been saved");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        totalScore = savedInstanceState.getInt(STATE_SCORE);
        name = savedInstanceState.getString(SAVE_NAME);
        if ((savedInstanceState != null) && savedInstanceState.containsKey("ChronoTime"))
            chronometer.setBase(savedInstanceState.getLong("ChronoTime"));
        super.onRestoreInstanceState(savedInstanceState);

        Log.v("OnRestoreInstanceState", "Your points have been restored");
    }

    //Result summary
    private String shareResultsMsg(int totalScore) {
        String mailResultMsg = "Hey, check out my score on Inventions That Change The World Quiz!";
        mailResultMsg += "\nI have got " + totalScore + " out of 8 points!";
        if (totalScore < 0) {
            mailResultMsg += "\nI got 0 out of 8 points!";
        }
        TextView name = (TextView) findViewById(R.id.usernameTextView);
        mailResultMsg += "\nYours, " + name.getText();
        return mailResultMsg;
    }

    //Intent - sending your result summary by email
    public void ShareYourResults(View view) {
        String mailResultMsg = shareResultsMsg(totalScore);
        Log.v("QuizActivity", "Display message here: " + mailResultMsg);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Invention That Change The World Results");
        intent.putExtra(Intent.EXTRA_TEXT, mailResultMsg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // Dismissing keyboard when click outside of EditText in android
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }
}
