package com.example.hakdog;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.os.CountDownTimer;
import android.widget.TextView;


public class map2 extends Activity {

    private static final long INITIAL_TIME = 60000; // 1 minute
    private long remainingTime = INITIAL_TIME;
    private CountDownTimer countDownTimer;
    private TextView textView4;
    private boolean jumpscareShown = false;

    private MediaPlayer mp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        final ImageButton imageButton13 = findViewById(R.id.imageButton13);
        final ImageButton imageButton15 = findViewById(R.id.imageButton15);

        stopAudioPlayback();

        //audio for the stall
        mp1 = MediaPlayer.create(map2.this, R. raw. thestall);
        mp1.start();

        textView4 = findViewById(R.id.textView4);

        imageButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long newTime = remainingTime - 5000;
                if (newTime < 0) {
                    newTime = 0;
                }
                updateRemainingTime(newTime);

                // Check if time is up and jumpscare hasn't been shown yet
                if (newTime == 0 && !jumpscareShown) {
                    navigateToJumpscare();
                }
            }
        });

        imageButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
                // Stop the timer when imageButton15 is clicked
                stopTimer();
                // Navigate to the Stalls activity
                Intent intent = new Intent(map2.this, Funhouse.class);
                startActivity(intent);
            }
        });

        startTimer();
    }

    private void updateRemainingTime(long newTime) {
        // Stop the current timer
        stopTimer();

        // Update remainingTime with the new time
        remainingTime = newTime;

        // Restart the timer with the updated remainingTime
        startTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(remainingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;

                updateTextView();
            }

            @Override
            public void onFinish() {

                if (!jumpscareShown) {
                    navigateToJumpscare();
                }
            }
        };

        countDownTimer.start();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void navigateToJumpscare() {

        stopAudioPlayback();

        Intent intent = new Intent(map2.this, jumpscare.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        finish();

        jumpscareShown = true;
    }

    private void updateTextView() {

        long remainingSeconds = remainingTime / 1000;

        String remainingTimeString = (remainingSeconds >= 0) ? String.valueOf(remainingSeconds) : "-" + String.valueOf(Math.abs(remainingSeconds));
        textView4.setText(remainingTimeString);
    }
    private void stopAudioPlayback() {
        if (mp1 != null) {
            mp1.stop();
            mp1.release();
            mp1 = null;
        }
    }
}


