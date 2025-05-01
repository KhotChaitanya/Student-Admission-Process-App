package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayData extends AppCompatActivity {
    String user;
    DBHelper DB;
    TextView username, email, fname, lname, mobile, address;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);
        fname = (TextView) findViewById(R.id.fname);
        lname = (TextView) findViewById(R.id.lname);
        mobile = (TextView) findViewById(R.id.mobile);
        address = (TextView) findViewById(R.id.address);
        btnback = (Button) findViewById(R.id.btnBack);

        Intent uname = getIntent();
        user = uname.getStringExtra("ukey");
        DB = new DBHelper(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Welcome.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

        Cursor cursor = DB.displayData(user);

        if(cursor.getCount()==0){
            Toast.makeText(DisplayData.this,"NO DATA FOUND",Toast.LENGTH_LONG).show();
            return;
        }

        username.setText(user);

        while(cursor.moveToNext()){
            email.setText(cursor.getString(1));
            fname.setText(cursor.getString(3));
            lname.setText(cursor.getString(4));
            mobile.setText(cursor.getInt(5)+"");
            address.setText(cursor.getString(6));
        }

    }
}
