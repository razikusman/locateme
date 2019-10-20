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
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;

public class LogIn extends AppCompatActivity {

    private Button btnsignup;
    private Button btnlogin;
    private EditText username;
    private EditText pasword;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnsignup = (Button) findViewById(R.id.btnsignup);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.txtUsername);
        pasword = (EditText) findViewById(R.id.txtpassword);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

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

        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    String pass;
    public void activitylogin(){

        String name = username.getText().toString();
        pass = pasword.getText().toString();


            if (databaseReference.child(name)!= null){

            databaseReference.child(name).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    User user = dataSnapshot.getValue(User.class);
                    if (pasword.getText().toString().equals(user.getPaswd()))
                    {
                        Toast.makeText(LogIn.this,
                                "Sign in",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LogIn.this, Passanger.class);
                        startActivity(intent);
                    }
                    else
                        {
                            Toast.makeText(LogIn.this,
                                    "username or password incorrect signup to create an account",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else{
            Toast.makeText(LogIn.this,
                    "username or password incorrect signup to create an account",Toast.LENGTH_LONG).show();
        }

    }

}
