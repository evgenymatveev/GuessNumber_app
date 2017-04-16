package ru.ematveev.guessnumber2;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TryAgain {

    public static void tryAgain(Button g, Button s, TextView t, EditText e) {
        s.setText(R.string.button_guess_5);
        t.setText("");
        e.setText("");
        g.setEnabled(false);
    }
}
