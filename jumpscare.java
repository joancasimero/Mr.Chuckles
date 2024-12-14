package com.example.hakdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class jumpscare extends AppCompatActivity {

    private MediaPlayer jumpscarePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumpscare);

        //Check if the device is in landscape mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Set the GIF as the background
            getWindow().setBackgroundDrawableResource(R.drawable.jumpy);

            jumpscarePlayer = MediaPlayer.create(this, R.raw.jumpscaresound);
            jumpscarePlayer.start();

            ImageView imageView = findViewById(R.id.imageView9);

            Glide.with(this)
                    .load(R.drawable.jumpy)
                    .into(imageView);
        }

        // Find the root layout
        View rootLayout = findViewById(R.id.jumpscare_root_layout);

// Set click listener to root layout
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Proceed to Results activity with fade out transition
                Intent intent = new Intent(jumpscare.this, Results.class);
                startActivity(intent);

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                onDestroy();
            }
        });
    }
    //removes the sound once the jumpscare is destroyed.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (jumpscarePlayer != null) {
            jumpscarePlayer.release();
            jumpscarePlayer = null;
        }
    }
    // Method to stop audio playback

}