package com.example.alegra08.tp2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.time.Instant;

import static android.app.AlarmManager.ELAPSED_REALTIME;

public class CountdownActivity extends AppCompatActivity {

    private static final int DURATION_REQUEST_CODE = 1;
    private static long countdown;
    private static long startTime = 0;
    private static boolean isactivated = false;
    private static Handler handler;
    private AlarmManager am;
    PendingIntent alarmIntent;
    /*


     */
    /*private Runnable getRefreshRunnable() {
        return refreshRunnable;
    }
    Runnable refreshRunnable = () -> {
        if (isactivated) {
            refresh();
            handler.postDelayed(getRefreshRunnable() , 500);
        }
    };*/

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        countdown = 10000;
        handler = new Handler();
        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        //getRefreshRunnable();

    }
    @Override
    protected void onPause(){
        super.onPause();
        alarmIntent = PendingIntent.getActivity(this, 1, new Intent(this, End_of_countdown.class), 0);
        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + countdown*1000, alarmIntent);
    }
    @Override
    protected void onResume(){
        super.onResume();
        alarmIntent = PendingIntent.getActivity(this, 1, new Intent(this, End_of_countdown.class), 0);
        am.cancel(alarmIntent);
    }

    public void validateOnClick(View v) {
        Intent intent = new Intent(this, DurationActivity.class);
        this.onPause();
        startActivityForResult(intent, DURATION_REQUEST_CODE);
    }
    public void startCountdown(View v) {
        isactivated = true;
        refresh();

        countDownTimer = new CountDownTimer(countdown * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                if(isactivated) {
                    countdown = millisUntilFinished / 1000;
                    TextView durationView = (TextView) findViewById(R.id.countdownTextView);
                    String print = new Long(countdown).toString();
                    durationView.setText(print);
                }
            }

            public void onFinish() {
            }
        };


        countDownTimer.start();
    }

    public void stopCountdown(View v) {

        isactivated = false;
        countDownTimer.cancel();
        refresh();
    }

    public void refreshCountdown(View v) {

        TextView durationView = (TextView) findViewById(R.id.countdownTextView);
        String print = new Long(countdown).toString();
        durationView.setText(print);
    }
    public void refresh() {
        if(isactivated) {
            ((Button) findViewById(R.id.stopButton)).setEnabled(true);
            ((Button) findViewById(R.id.refreshButton)).setEnabled(true);
            ((Button) findViewById(R.id.startButton)).setEnabled(false);
        } else {
            ((Button) findViewById(R.id.startButton)).setEnabled(true);
            ((Button) findViewById(R.id.stopButton)).setEnabled(false);
            ((Button) findViewById(R.id.refreshButton)).setEnabled(false);
        }
        /*long t = System.currentTimeMillis()/1000;
        long remaining = countdown - (t - startTime);
        TextView durationView = (TextView) findViewById(R.id.countdownTextView);
        String print = new Long(remaining).toString();
        durationView.setText(print);*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Integer change = data.getIntExtra( "duration", 0);
        countdown = new Long(change);
        TextView durationView = (TextView) findViewById(R.id.countdownTextView);
        String print = change.toString();
        durationView.setText(print);
    }
}
