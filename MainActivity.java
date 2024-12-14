package com.example.hakdog;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Set the GIF as the background
            getWindow().setBackgroundDrawableResource(R.drawable.s1);

            ImageView imageView = findViewById(R.id.imageview1);

            Glide.with(this)
                    .load(R.drawable.s1)
                    .into(imageView);

        }

        // Find the imageButton2
        ImageButton imageButton2 = findViewById(R.id.imageButton2);

// Set click listener to imageButton2
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Apply fade transition
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                // Close the app
                finish();
            }
        });

        // Find the imageButton1
        ImageButton imageButton1 = findViewById(R.id.imageButton1);

// Set click listener to imageButton1
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed to wheel activity with fade transition effect
                Intent intent = new Intent(MainActivity.this, Wheel.class);
                startActivity(intent);
                // Apply fade transition
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                // Close the current activity
                finish();
            }
        });

    }
}