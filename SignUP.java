package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText edtusername, edtemail, edtpassword, edtfname, edtlname, edtmobile, edtaddress;
    Button btncreate, btnback;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtusername = (EditText) findViewById(R.id.edtusername);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        edtfname = (EditText) findViewById(R.id.edtfname);
        edtlname = (EditText) findViewById(R.id.edtlname);
        edtmobile = (EditText) findViewById(R.id.edtmobile);
        edtaddress = (EditText) findViewById(R.id.edtaddress);
        btncreate = (Button) findViewById(R.id.btncreate);
        btnback = (Button) findViewById(R.id.btnback);
        DB = new DBHelper(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtusername.getText().toString();
                String email = edtemail.getText().toString();
                String password = edtpassword.getText().toString();
                String firstname = edtfname.getText().toString();
                String lastname = edtlname.getText().toString();
                Integer mobile = Integer.parseInt(edtmobile.getText().toString());
                String address = edtaddress.getText().toString();

                if(username.equals("")||email.equals("")||password.equals("")||firstname.equals("")||lastname.equals("")||(mobile==0)||address.equals("")){
                    Toast.makeText(SignUp.this,"Please enter all the fields",Toast.LENGTH_LONG).show();
                }
                else{
                    Boolean checkuser = DB.checkusername(username);
                    if(checkuser == false){
                        Boolean insert = DB.insertData(username,email,password,firstname,lastname,mobile,address);
                        if(insert == true){
                            Toast.makeText(SignUp.this,"Registered successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),Welcome.class);
                            intent.putExtra("key",username);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SignUp.this,"Registration failed",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUp.this,"User already exists, Please sign in",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
