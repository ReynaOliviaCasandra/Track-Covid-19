package com.codewithshubh.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button registt;
    EditText emaill, passwordd;
    Button exit, btn_bersih;
    ImageButton loginn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        exit = (Button)findViewById(R.id.keluar);
        btn_bersih = (Button) findViewById(R.id.bersihkan);
        emaill = (EditText) findViewById(R.id.email);
        passwordd = (EditText) findViewById(R.id.password);
        loginn = (ImageButton) findViewById(R.id.login);
        registt = (Button) findViewById(R.id.btn_regist);

        btn_bersih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emaill.getText().clear();
                passwordd.getText().clear();
                emaill.requestFocus();
            }
        });

        //Register
        registt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //Login
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = emaill.getText().toString();
                String strPassword = passwordd.getText().toString();
                Boolean masuk = db.checkLogin(strEmail, strPassword);
                if (masuk == true) {
                    Boolean updateSession = db.upgradeSession("ada", 1);
                    if (updateSession == true) {
                        Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal Masuk", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //logout
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}