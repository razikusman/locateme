package com.example.locateme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    private ImageButton btnpasanger;
    private ImageButton btnservice;
    private Button btnpasanger1;
    private Button btnservice1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnpasanger = (ImageButton) findViewById(R.id.btnpasssanger);
        btnservice = (ImageButton) findViewById(R.id.btnservice);
        btnpasanger1 = (Button) findViewById(R.id.btnpasssanger1);
        btnservice1 = (Button) findViewById(R.id.btnservice1);



        btnpasanger1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPassanger();
            }
        });

        btnservice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityService();
            }
        });
    }

    public void activityPassanger(){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void activityService(){
        Intent intent = new Intent(this, LogInservice.class);
        startActivity(intent);

    }
}
