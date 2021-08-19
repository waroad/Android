package com.example.quicknesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        String size = getIntent().getStringExtra("size");
        String mode = getIntent().getStringExtra("mode");
        Toast.makeText(this, size, Toast.LENGTH_SHORT).show();
    }
}
