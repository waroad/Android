package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends Activity {
    SQLiteDatabase sd;
    TextView body, title;
    String action;
    Button sub_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        body=findViewById(R.id.body);
        title=findViewById(R.id.title);
        Intent i = getIntent();
        sub_button = (Button) findViewById(R.id.button);
        action = i.getStringExtra("ACTION");
        switch (action){
            case "READ":
                sub_button.setText("뒤로가기");
                title.setEnabled(false);
                title.setText(i.getStringExtra("TITLE"));
                body.setText(i.getStringExtra("TEXT"));
                body.setEnabled(false);
                sub_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                break;
            case "WRITE":
                sd=openOrCreateDatabase("memo.db",0,null);
                title.setText("");
                body.setText("");
                title.setHint("제목");
                body.setHint("내용");
                sub_button.setText("저장");
                break;
        }
        sub_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (action){
                    case "READ":
                        finish();
                        break;
                    case "WRITE":
                        ContentValues values = new ContentValues();
                        values.put("title",title.getText().toString());
                        values.put("body",body.getText().toString());
                        sd.insert("note",null,values);
                        finish();
                        break;
                }
            }
        });
    }
}