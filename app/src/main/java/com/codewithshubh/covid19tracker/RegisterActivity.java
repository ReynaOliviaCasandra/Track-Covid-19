package com.codewithshubh.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button loginn;
    EditText usernamee, emaill, passwordd, conpass;
    ImageButton registt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        usernamee = (EditText) findViewById(R.id.username);
        emaill = (EditText) findViewById(R.id.email);
        passwordd = (EditText) findViewById(R.id.password);
        conpass = (EditText) findViewById(R.id.confirm);
        loginn = (Button) findViewById(R.id.login);
        registt = (ImageButton) findViewById(R.id.btn_regist);

        //login
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
        registt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struname = usernamee.getText().toString();
                String strEmail = emaill.getText().toString();
                String strPassword = passwordd.getText().toString();
                String strConfirm = conpass.getText().toString();
                if (strPassword.equals(strConfirm)){
                    Boolean daftar = db.insertUser(strEmail, strPassword);
                    if (daftar == true) {
                        Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Register Gagal", Toast.LENGTH_SHORT ).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}