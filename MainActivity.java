package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtusername, txtpassword;
    Button btnSign, btnSignUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        btnSign = (Button) findViewById(R.id.btnSign);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        DB = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtusername.getText().toString();
                String password = txtpassword.getText().toString();

                if(username.equals("")||password.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter details in all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean check = DB.checkusernamepassword(username,password);
                    if(check == true){
                        Toast.makeText(MainActivity.this,"Login successfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Welcome.class);
                        intent.putExtra("key",username);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
