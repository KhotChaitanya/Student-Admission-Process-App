package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    TextView txtu;
    Button btnlogout, btnupdate, btndisplay;
    DBHelper DB;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnlogout = (Button) findViewById(R.id.btnLogout);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btndisplay = (Button) findViewById(R.id.btndisplay);
        txtu = (TextView) findViewById(R.id.txtu);
        DB = new DBHelper(this);
        Intent uname = getIntent();
        username = uname.getStringExtra("key");

        txtu.setText(String.format("Welcome %s", username));

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Welcome.this,"Logged Off successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UpdateData.class);
                intent.putExtra("key",username);
                startActivity(intent);
            }
        });

        btndisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DisplayData.class);
                intent.putExtra("ukey",username);
                startActivity(intent);
            }
        });
    }
}
