package com.example.alegra08.tp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextWatcher;

public class DurationActivity extends AppCompatActivity {

    private static final int DURATION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);

        EditText seconds = (EditText)findViewById(R.id.seconds);
        seconds.addTextChangedListener(numberWatcher(seconds));
        EditText minutes = (EditText)findViewById(R.id.minutes);
        minutes.addTextChangedListener(numberWatcher(minutes));
    }
    public void validateOnClick(View v) {
        int hours = 0, minutes = 0, seconds = 0;
        try {
            hours = Integer.parseInt(((EditText)findViewById(R.id.hours)).getText().toString());
        }
        catch (NumberFormatException e) {
        }
        try {
            minutes = Integer.parseInt(((EditText)findViewById(R.id.minutes)).getText().toString());
        }
        catch (NumberFormatException e) {
        }
        try {
            seconds = Integer.parseInt(((EditText)findViewById(R.id.seconds)).getText().toString());
        }
        catch (NumberFormatException e) {
        }

        Integer total = hours * 3600 + minutes * 60 + seconds;
        Toast t = Toast.makeText(this, total.toString(), Toast.LENGTH_SHORT);
        t.show();

        Intent duration = new Intent(this, CountdownActivity.class);
        duration.putExtra("duration", total); // put the countdown
        int resultCode = RESULT_OK;
        setResult(resultCode, duration);
        finish();

    }
    private TextWatcher numberWatcher(final EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TODO
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) return;
                else if (Integer.parseInt(s.toString()) > 60)
                    editText.setError("Invalid value : must be between 0 and 59");
            }
        };
    }
}
