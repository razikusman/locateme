package com.example.locateme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spalshDisplay();
    }



    private static int TIME_OUT = 4000; //Time to launch the another activity
    Handler handler;

    private void spalshDisplay() {

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this , DashBoard.class);
                startActivity(intent);
                finish(); // finish the activity
            }
        },TIME_OUT);
    }
}