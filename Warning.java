package com.example.hakdog;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Warning extends AppCompatActivity {

    private MediaPlayer warningmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        // Check if the device is in landscape mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Set the GIF as the background
            getWindow().setBackgroundDrawableResource(R.drawable.warning1);

            ImageView imageView = findViewById(R.id.imageView2);

            Glide.with(this)
                    .load(R.drawable.warning1)
                    .into(imageView);

            stopAudioPlayback();

            warningmp = MediaPlayer.create(Warning.this, R.raw.warning);
            warningmp.start();

        }

        // Add click listener to the warning layout
        findViewById(R.id.warningLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
                // Proceed to the main activity with fade transition effect
                Intent intent = new Intent(Warning.this, MainActivity.class);
                startActivity(intent);
                // Apply fade transition
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                // Finish current activity if you want to prevent the user from coming back here using the back button
                finish();
            }
        });
    }
    private void stopAudioPlayback() {
        if (warningmp != null) {
            warningmp.stop();
            warningmp.release();
            warningmp = null;
        }
    }

}