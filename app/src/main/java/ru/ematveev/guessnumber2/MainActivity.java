package ru.ematveev.guessnumber2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button_guess;
    private Button buttonStart;
    private TextView text_view;
    private TextView text_view_attemp;
    private EditText editText;
    private int resAttempt;
    private int numberAttempt = 3;
    private String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_guess = (Button) findViewById(R.id.button_guess);
        buttonStart = (Button) findViewById(R.id.button_start);
        text_view = (TextView) findViewById(R.id.text_view);
        text_view_attemp = (TextView) findViewById(R.id.text_view_attemp);
        editText = (EditText) findViewById(R.id.editText);

        text_view.setText("Сыграем?)");
        button_guess.setEnabled(false);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAttempt = 3;
                editText.setText("");
                GenerateNumberController g = new GenerateNumberController(new Random());
                resAttempt = g.generate(10);
                text_view.setText(R.string.text_button_start);
                text_view_attemp.setText(R.string.text_button_start_attempt);
                button_guess.setEnabled(true);
            }
        });

        button_guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty()) {
                    text_view.setText(R.string.button_guess_1);
                } else {
                    int guess = Integer.parseInt(editText.getText().toString());
                    final GuessResult result = GenerateNumberController.numberEqualityCheck(guess, resAttempt);
                    if (result == GuessResult.GUESS_IS_BIGGER) {
                        numberAttempt--;
                        text_view.setText(R.string.button_guess_2);
                        switch (numberAttempt) {
                            case 1:
                                x = "попытка";
                                break;
                            default:
                                x = "попытки";
                        }
                        text_view_attemp.setText("Осталось" + " " + numberAttempt + " " + x);
                        editText.setText("");
                        if (numberAttempt == 0) {
                            text_view.setText("Вы не угадали!)" + "\n" + "Попробуйте еще раз!");
                            TryAgain.tryAgain(button_guess, buttonStart, text_view_attemp, editText);
                        }
                    } else if (result == GuessResult.GUESS_IS_SMALL) {
                        numberAttempt--;
                        text_view.setText(R.string.button_guess_6);
                        switch (numberAttempt) {
                            case 1:
                                x = "попытка";
                                break;
                            default:
                                x = "попытки";
                        }
                        text_view_attemp.setText("Осталось" + " " + numberAttempt + " " + x);
                        editText.setText("");
                        if (numberAttempt == 0) {
                            text_view.setText("Вы не угадали!)" + "\n" + "Попробуйте еще раз!");
                            TryAgain.tryAgain(button_guess, buttonStart, text_view_attemp, editText);
                        }
                    } else {
                        if (result == GuessResult.EQUAL) {
                            text_view.setText("Вы угадали!" + "\n" + "Игра окончена!");
                            TryAgain.tryAgain(button_guess, buttonStart, text_view_attemp, editText);
                        }
                    }
                }
            }

        });
    }
}

