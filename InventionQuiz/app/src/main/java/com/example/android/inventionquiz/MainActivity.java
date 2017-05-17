package com.example.android.inventionquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Fields that will store  EditText and Button
    public static final String USERNAME = "Intent.EXTRA_TEXT";

    private EditText mUsername;
    private Button mStartQuizActivity;

    // Hide Keyboard after clicking
    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Using findViewById to get a reference to Button from xml.
    public void startQuiz(View view) {
        mStartQuizActivity = (Button) findViewById(R.id.startButton);
        mUsername = (EditText) findViewById(R.id.username);

        //Retrieve the text from the EditText and store it in a variable
        String username = mUsername.getText().toString();

        //Retrieve the text from the EditText and store it in a variable
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(getApplicationContext(), "Hello, " + username + ". Good luck!", Toast.LENGTH_SHORT).show();
        }

        //Opening Quiz (Main2Activity) and passing intent
        Context context = MainActivity.this;
        Intent startChildActivityIntent = new Intent(context, QuizActivity.class);
        startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, username);
        startActivity(startChildActivityIntent);
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