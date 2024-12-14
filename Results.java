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

public class Results extends AppCompatActivity {

    private MediaPlayer gmmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Set the GIF as the background
            getWindow().setBackgroundDrawableResource(R.drawable.gameover);

            ImageView imageView = findViewById(R.id.imageView5);

            Glide.with(this)
                    .load(R.drawable.gameover)
                    .into(imageView);

            stopAudioPlayback();

            //audio for the stall
            gmmp = MediaPlayer.create(Results.this, R. raw. gameover);
            gmmp.start();
        }

        // Find the imageButton7
        ImageButton imageButton7 = findViewById(R.id.imageButton7);

// Set click listener to imageButton7
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
                // Navigate to Wheel activity with fade transition
                Intent intent = new Intent(Results.this, Wheel.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

// Find the imageButton6
        ImageButton imageButton6 = findViewById(R.id.imageButton6);

// Set click listener to imageButton6
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
                // Navigate to MainActivity with fade transition
                Intent intent = new Intent(Results.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
    private void stopAudioPlayback() {
        if (gmmp != null) {
            gmmp.stop();
            gmmp.release();
            gmmp = null;
        }
    }
}