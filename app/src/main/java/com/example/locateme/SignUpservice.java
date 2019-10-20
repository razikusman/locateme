package com.example.locateme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpservice extends AppCompatActivity {
    private Button btncreate;
    private EditText Username;
    private EditText Email;
    private EditText Paswrd;
    private EditText CPaswrd;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upservice);

        databaseReference = FirebaseDatabase.getInstance().getReference("Service");

        btncreate = (Button) findViewById(R.id.btncreate);
        Username = (EditText) findViewById(R.id.txtuname);
        Email = (EditText) findViewById(R.id.txtmail);
        Paswrd = (EditText) findViewById(R.id.txtpwrd);
        CPaswrd = (EditText) findViewById(R.id.txtcpwrd);

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitycreate();
            }
        });
    }

    public void activitycreate(){

        String name = Username.getText().toString();
        String mail = Email.getText().toString();
        String password = Paswrd.getText().toString();
        String cpassword = CPaswrd.getText().toString();

        if (TextUtils.isEmpty((name)) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)) {
            Intent intent = new Intent(this, SignUpservice.class);
            startActivity(intent);
        }
        else {
            if(cpassword.toString() != password.toString()) {
                Toast.makeText(SignUpservice.this,"Successfully Created",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ServiceProvider.class);
                startActivity(intent);
                Service service = new Service(name,mail,password);

                databaseReference.child(name).setValue(service);
                Username.setText("");
                Email.setText("");
                Paswrd.setText("");
                CPaswrd.setText("");
            }

        }
    }
}
