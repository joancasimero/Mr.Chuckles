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

public class Funhouse extends AppCompatActivity {

    private MediaPlayer fnmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funhouse);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Set the GIF as the background
            getWindow().setBackgroundDrawableResource(R.drawable.funhouse);

            ImageView imageView = findViewById(R.id.imageView4);

            Glide.with(this)
                    .load(R.drawable.funhouse)
                    .into(imageView);

            stopAudioPlayback();

            //audio for the wheel
            fnmp = MediaPlayer.create(Funhouse.this, R. raw. funhousetitle);
            fnmp.start();
        }

        // Find the imageButton11
        ImageButton imageButton11 = findViewById(R.id.imageButton11);

// Set click listener to imageButton11
        imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
                // Navigate to howtoplay activity with fade transition
                Intent intent = new Intent(Funhouse.this, howtoplay.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Find the imageButton5
        ImageButton imageButton5 = findViewById(R.id.imageButton5);

// Set click listener to imageButton5
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
                // Navigate to map3 activity with fade transition
                Intent intent = new Intent(Funhouse.this, map3.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
    private void stopAudioPlayback() {
        if (fnmp != null) {
            fnmp.stop();
            fnmp.release();
            fnmp = null;
        }
    }
}