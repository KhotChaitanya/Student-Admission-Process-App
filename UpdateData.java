package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {
    EditText edtemail2, edtpassword2, edtfname2, edtlname2, edtmobile2, edtaddress2;
    Button btnsave;
    DBHelper DB;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        edtemail2 = (EditText) findViewById(R.id.edtemail2);
        edtpassword2 = (EditText) findViewById(R.id.edtpassword2);
        edtfname2 = (EditText) findViewById(R.id.edtfname2);
        edtlname2 = (EditText) findViewById(R.id.edtlname2);
        edtmobile2 = (EditText) findViewById(R.id.edtmobile2);
        edtaddress2 = (EditText) findViewById(R.id.edtaddress2);
        btnsave = (Button) findViewById(R.id.btnsave);
        DB = new DBHelper(this);

        Intent uname = getIntent();
        username = uname.getStringExtra("key");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail2.getText().toString();
                String password = edtpassword2.getText().toString();
                String firstname = edtfname2.getText().toString();
                String lastname = edtlname2.getText().toString();
                Integer mobile = Integer.parseInt(edtmobile2.getText().toString());
                String address = edtaddress2.getText().toString();

                if (username.equals("") || email.equals("") || password.equals("") || firstname.equals("") || lastname.equals("") || (mobile == 0) || address.equals("")) {
                    Toast.makeText(UpdateData.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                } else {
                    Boolean update = DB.updateData(username, email, password, firstname, lastname, mobile, address);
                    if (update == true) {
                        Toast.makeText(UpdateData.this, "Details updated successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Welcome.class);
                        intent.putExtra("key",username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(UpdateData.this, "Failed to update the details", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
