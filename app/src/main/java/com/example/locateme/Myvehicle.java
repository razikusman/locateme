package com.example.locateme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media.app.NotificationCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Myvehicle extends AppCompatActivity {
    private Button add;
    private EditText Username;
    private EditText pasword;
    DatabaseReference databaseReference;
    ListView lv;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> List = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvehicle);

        pasword = (EditText) findViewById(R.id.txtpassword);
        add = (Button) findViewById(R.id.btnaddmy);
        Username = (EditText) findViewById(R.id.txtuname);


        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle");
        lv = (ListView) findViewById(R.id.vehiclelist);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(Vehicle.class).toString();
                arrayList.add(value);
                List.add(dataSnapshot.getKey());
                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(Vehicle.class).toString();
                arrayList.remove(value);
                List.remove(dataSnapshot.getKey());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityadd();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            String item;
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                item = List.get(position);
                new AlertDialog.Builder(Myvehicle.this)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                databaseReference.child(item).removeValue();
                                arrayList.remove(item);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(Myvehicle.this,
                                        "Vehicle Successfuly Deleted",Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton("No" , null)
                        .show();
                return true;
            }
        });

    }

    private void activityadd() {

        Intent intent = new Intent(this, AddVehicle.class);
        startActivity(intent);
    }
}
