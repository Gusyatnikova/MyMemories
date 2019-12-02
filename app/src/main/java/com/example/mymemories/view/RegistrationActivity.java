package com.example.mymemories.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymemories.R;
import com.example.mymemories.controller.RegistrationController;

public class RegistrationActivity extends AppCompatActivity {
    private EditText login;
    private EditText pswd;
    private EditText email;
    static RegistrationController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        login = findViewById(R.id.login);
        pswd = findViewById(R.id.password);
        email = findViewById(R.id.email);
        controller = new RegistrationController(this.getApplicationContext());
    }
    public void registrate(View view){
        String _login = login.getText().toString();
        String _pswd = pswd.getText().toString();
        String _email = email.getText().toString();
        controller.registrate(_login, _pswd, _email);
        //Intent intent = new Intent(this, next.class);
        //startActivity(intent);
    }
}
