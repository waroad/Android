package com.example.quicknesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    TextView mTextField;
    int score=0;
    Button b1,b2,b3,b4;
    Random rand = new Random();
    private CountDownTimer countDownTimer;
    private double count = 10.0;
    int ar=5;
    int[] arr = {1,2,3,4};
    public int getSmallest(int[] a){
        Arrays.sort(a);
        return a[0];
    }
    class MyListener implements View.OnClickListener {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn1:
                    if (b1.getText().equals(Integer.toString(getSmallest(arr)))){
                        int t=getSmallest(arr)+rand.nextInt(10)+1;
                        b1.setText(t+"");
                        arr[0]=t;
                        score+=1;
                    }
                    else{
                        count-=1;
                    }
                    break;
                case R.id.btn2:
                    if (b2.getText().equals(Integer.toString(getSmallest(arr)))){
                        int t=getSmallest(arr)+rand.nextInt(10)+1;
                        b2.setText(t+"");
                        arr[0]=t;
                        score+=1;
                    }
                    else{
                        count-=1;
                    }
                    break;
                case R.id.btn3:
                    if (b3.getText().equals(Integer.toString(getSmallest(arr)))){
                        int t=getSmallest(arr)+rand.nextInt(10)+1;
                        b3.setText(t+"");
                        arr[0]=t;
                        score+=1;
                    }
                    else{
                        count-=1;
                    }
                    break;
                default:
                    if (b4.getText().equals(Integer.toString(getSmallest(arr)))){
                        int t=getSmallest(arr)+rand.nextInt(10)+1;
                        b4.setText(t+"");
                        arr[0]=t;
                        score+=1;
                    }
                    else{
                        count-=1;
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        MyListener m = new MyListener();
        mTextField=(TextView) findViewById(R.id.sec);
        String size = getIntent().getStringExtra("size");
        String mode = getIntent().getStringExtra("mode");
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        b1.setOnClickListener(m);
        b2.setOnClickListener(m);
        b3.setOnClickListener(m);
        b4.setOnClickListener(m);
        countDownTimer();
        countDownTimer.start();


    }
    public void countDownTimer(){
        countDownTimer = new CountDownTimer(10000, 100) {
            public void onTick(long millisUntilFinished) {
                if(count<=0){
                    onFinish();
                    countDownTimer.cancel();
                }
                mTextField.setText(String.format("%.1f",count));
                count -=0.1;
            }
            public void onFinish() {
                Intent i = new Intent(PlayActivity.this,ResultActivity.class);
                i.putExtra("score",score+"");
                startActivity(i);
                finish();
            }
        };
    }

}