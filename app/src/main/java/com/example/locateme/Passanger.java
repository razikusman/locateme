package com.example.locateme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Passanger extends AppCompatActivity {


    private Button btngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passanger);

        btngo = (Button) findViewById(R.id.btngo);

        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activtymap();
            }
        });
    }

    public void activtymap() {
        Intent intent = new Intent(this, Locatememap.class);
        startActivity(intent);
    }
}