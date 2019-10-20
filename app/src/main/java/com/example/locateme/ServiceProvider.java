package com.example.locateme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceProvider extends AppCompatActivity {
    private Button btnmy;
    private Button btnadd;
    private Button btndlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);

        btnmy = (Button) findViewById(R.id.btnmy);
        btnadd = (Button) findViewById(R.id.btnaddmy);
        btndlt = (Button) findViewById(R.id.btndltmy);

        btnmy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitymy();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityadd();
            }
        });

        btndlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitymy();
            }
        });
    }

    private void activityadd() {
        Intent intent = new Intent(this, AddVehicle.class);
        startActivity(intent);
    }

    public void activitymy(){
        Intent intent = new Intent(this, Myvehicle.class);
        startActivity(intent);
    }

}
