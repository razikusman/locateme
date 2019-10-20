package com.example.locateme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInservice extends AppCompatActivity {

    private Button btnsignup;
    private Button btnlogin;
    private EditText username;
    private EditText pasword;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_inservice);

        btnsignup = (Button) findViewById(R.id.btnsignup);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.txtUsername);
        pasword = (EditText) findViewById(R.id.txtpassword);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Service");

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitySignUp();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitylogin();
            }
        });
    }

    public void activitySignUp(){
        Intent intent = new Intent(this, SignUpservice.class);
        startActivity(intent);
    }


    String name,pass;
    public void activitylogin(){

         name = username.getText().toString();
         pass = pasword.getText().toString();

         try {
             databaseReference.child(name).addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {

                     Service service = dataSnapshot.getValue(Service.class);
                     if (pasword.getText().toString().equals(service.getPassword()))
                     {

                         Toast.makeText(LogInservice.this,
                                 "Sign in",Toast.LENGTH_LONG).show();

                         Intent intent = new Intent(LogInservice.this, ServiceProvider.class);
                         startActivity(intent);
                     }
                     else
                         {

                         Toast.makeText(LogInservice.this,
                                 "username or password incorrect signup to create an account",Toast.LENGTH_LONG).show();
                     }

                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });
         }
         catch (NullPointerException e){
             Toast.makeText(LogInservice.this, "fail",Toast.LENGTH_LONG).show();
         }

    }
}