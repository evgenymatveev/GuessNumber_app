package ru.ematveev.guessnumber2;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetFieldStart {

    /**
     * The method clears the field textViewFeedBacktextViewFeedBack and editTextInputNumber. Change
     * the name of the button start and deactivates button guess.
     *
     * @param guess               the botton of guessing the numberof guessing the number.
     * @param start               the button of the starting the game.
     * @param textViewFeedBack    field for message about the game.
     * @param editTextInputNumber field number input by the user.
     */
    public static void setInitState(Button guess, Button start,
                                    TextView textViewFeedBack, EditText editTextInputNumber) {
        start.setText(R.string.button_start_new);
        textViewFeedBack.setText("");
        editTextInputNumber.setText("");
        guess.setEnabled(false);
    }
}
