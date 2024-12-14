package com.example.hakdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

//public class map3 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map3);
//
//            // Find the imageButton14
//            ImageButton imageButton14 = findViewById(R.id.imageButton14);
//
//// Set click listener to imageButton14
//            imageButton14.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Navigate to jumpscare activity with fade out transition
//                    Intent intent = new Intent(map3.this, jumpscare.class);
//                    startActivity(intent);
//                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                }
//            });
//
//// Find the imageButton8
//            ImageButton imageButton8 = findViewById(R.id.imageButton8);
//
//// Set click listener to imageButton8
//            imageButton8.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Navigate to youfoundhim activity
//                    Intent intent = new Intent(map3.this, youfoundhim.class);
//                    startActivity(intent);
//                }
//            });
//        }
//    }

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class map3 extends AppCompatActivity {

    private static final long INITIAL_TIME = 30000; // 30 seconds
    private long remainingTime = INITIAL_TIME;
    private CountDownTimer countDownTimer;
    private TextView textView5;
    private boolean jumpscareShown = false;

    private MediaPlayer mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map3);

        final ImageButton imageButton14 = findViewById(R.id.imageButton14);
        final ImageButton imageButton8 = findViewById(R.id.imageButton8);

        textView5 = findViewById(R.id.textView5);

        stopAudioPlayback();

        mp2 = MediaPlayer.create(map3.this, R. raw. thefunhouse);
        mp2.start();

        imageButton14.setOnClickListener(new View.OnClickListener() {
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

        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopAudioPlayback();
                // Stop the timer when imageButton8 is clicked
                stopTimer();
                // Navigate to the youfoundhim activity
                Intent intent = new Intent(map3.this, youfoundhim.class);
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

        Intent intent = new Intent(map3.this, jumpscare.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        finish();

        jumpscareShown = true;
    }

    private void updateTextView() {

        long remainingSeconds = remainingTime / 1000;

        String remainingTimeString = (remainingSeconds >= 0) ? String.valueOf(remainingSeconds) : "-" + String.valueOf(Math.abs(remainingSeconds));
        textView5.setText(remainingTimeString);
    }
    private void stopAudioPlayback() {
        if (mp2 != null) {
            mp2.stop();
            mp2.release();
            mp2 = null;
        }
    }
}