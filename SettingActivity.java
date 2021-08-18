package com.example.quicknesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends AppCompatActivity {
    Button btnStart;
    RadioGroup size,mode;
    int siz=2,mod=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btnStart = (Button) findViewById(R.id.btnStart);
        size = findViewById(R.id.rGroupSize);
        size.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rBtn2x2) siz=2;
                if(i==R.id.rBtn3x3) siz=3;
                if(i==R.id.rBtn4x4) siz=4;
            }
        });
        mode = findViewById(R.id.rGroupMod);
        mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rBtnModDef) mod=1;
                if(i==R.id.rBtnModMem) mod=2;
                if(i==R.id.rBtnModTime) mod=3;
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingActivity.this,PlayActivity.class);
                i.putExtra("size",siz+"");
                i.putExtra("mode",mod+"");
                startActivity(i);
                finish();
            }
        });
    }
}