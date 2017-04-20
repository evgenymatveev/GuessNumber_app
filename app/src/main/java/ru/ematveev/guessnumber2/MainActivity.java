package ru.ematveev.guessnumber2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final static int DEFAULT_ATTEMPT_COUNT = 3;
    private final GenerateNumberController g = new GenerateNumberController(new Random());
    private GuessResult result;
    private Button buttonGuess;
    private Button buttonStart;
    private TextView textViewFeedBack;
    private TextView textViewAttempt;
    private EditText editTextInputNumber;
    private int resAttempt;
    private int numberAttempt = DEFAULT_ATTEMPT_COUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGuess = (Button) findViewById(R.id.button_guess);
        buttonStart = (Button) findViewById(R.id.button_start);
        textViewFeedBack = (TextView) findViewById(R.id.text_view_feed_back);
        textViewAttempt = (TextView) findViewById(R.id.text_view_attempt);
        editTextInputNumber = (EditText) findViewById(R.id.edit_text_input_number);

        textViewFeedBack.setText(R.string.text_view_greeting);
        buttonGuess.setEnabled(false);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAttempt = DEFAULT_ATTEMPT_COUNT;
                editTextInputNumber.setText("");
                resAttempt = g.generate(10);
                textViewFeedBack.setText(R.string.text_view_start);
                textViewAttempt.setText(R.string.text_start_attempt);
                editTextInputNumber.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (editTextInputNumber.getText().toString().isEmpty()) {
                            buttonGuess.setEnabled(false);
                        } else {
                            buttonGuess.setEnabled(true);
                        }
                    }
                });
            }
        });

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            final String x = "\n";
            @Override
            public void onClick(View v) {
                final int guess = Integer.parseInt(editTextInputNumber.getText().toString());
                result = GenerateNumberController.numberEqualityCheck(guess, resAttempt);
                if (result == GuessResult.GUESS_IS_BIGGER ||
                        result == GuessResult.GUESS_IS_SMALL) {
                    numberAttempt--;
                    if (result == GuessResult.GUESS_IS_BIGGER) {
                        textViewFeedBack.setText(R.string.text_view_number_bigger);
                    } else {
                        textViewFeedBack.setText(R.string.text_view_number_smaller);
                    }
                    String strOld = getResources().getString(R.string.text_view_attempt_number);
                    String strNew = String.format(strOld, numberAttempt);
                    textViewAttempt.setText(strNew);
                    editTextInputNumber.setText("");
                    if (numberAttempt == 0) {
                        String s = getResources().getString(R.string.text_view_feed_back_no_guess);
                        String sNew = String.format(s, x);
                        textViewFeedBack.setText(sNew);
                        SetFieldStart.setInitState(buttonGuess, buttonStart,
                                textViewAttempt, editTextInputNumber);
                    }
                } else {
                    if (result == GuessResult.EQUAL) {
                        String s2 = getResources().getString(R.string.text_view_feed_back_guess);
                        String sNew2 = String.format(s2, x);
                        textViewFeedBack.setText(sNew2);
                        SetFieldStart.setInitState(buttonGuess, buttonStart,
                                textViewAttempt, editTextInputNumber);
                    }
                }
            }
        });
    }
}










