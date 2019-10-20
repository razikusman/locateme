package com.example.locateme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddVehicle extends AppCompatActivity {

    private Button create;
    EditText vnumber,type,from,to;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        vnumber= (EditText) findViewById(R.id.txtvehiclenumber);
        type= (EditText) findViewById(R.id.txttype);
        from= (EditText) findViewById(R.id.txttfrom);
        to= (EditText) findViewById(R.id.txttto);

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle");

        create = (Button) findViewById(R.id.btncreate);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitymyvehicle();
            }
        });
    }

    private void activitymyvehicle() {

        String number = vnumber.getText().toString();
        String Type = type.getText().toString();
        String From = from.getText().toString();
        String To = to.getText().toString();

        Vehicle vehicle = new Vehicle(number,Type,From,To);

        databaseReference.child(number).setValue(vehicle);

        Intent intent = new Intent(this, Myvehicle.class);
        startActivity(intent);
    }


}
