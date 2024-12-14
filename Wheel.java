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

public class Wheel extends AppCompatActivity {

    private MediaPlayer whmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Set the GIF as the background
            getWindow().setBackgroundDrawableResource(R.drawable.wheel1);

            ImageView imageView = findViewById(R.id.imageView);

            Glide.with(this)
                    .load(R.drawable.wheel1)
                    .into(imageView);

            stopAudioPlayback();

            //audio for the wheel
            whmp = MediaPlayer.create(Wheel.this, R. raw. wheeltitle);
            whmp.start();

        }

        // Find the imageButton
        ImageButton imageButton = findViewById(R.id.imageButton);

// Set click listener to imageButton
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
                // Navigate to howtoplay activity with fade transition
                Intent intent = new Intent(Wheel.this, howtoplay.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

// Find the imageButton3
        ImageButton imageButton3 = findViewById(R.id.imageButton3);

// Set click listener to imageButton3
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
                // Navigate to map1 activity with fade transition
                Intent intent = new Intent(Wheel.this, map1.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
    private void stopAudioPlayback() {
        if (whmp != null) {
            whmp.stop();
            whmp.release();
            whmp = null;
        }
    }
}