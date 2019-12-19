package com.example.mymemories.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymemories.R;
import com.example.mymemories.controller.AuthorizationController;
import com.example.mymemories.controller.CustomException;
import com.example.mymemories.controller.NotesController;

public class Authorization extends AppCompatActivity {
    private EditText login;
    private EditText password;
    static AuthorizationController controller;
    static NotesController notesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        controller = new AuthorizationController(this.getApplicationContext());
        notesController = new NotesController(this.getApplicationContext());
    }

    public void authorization(View view) {
        String _login = login.getText().toString();
        String _password = password.getText().toString();
        try {
            controller.enter(_login, _password);
        } catch (CustomException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            login.setText("");
            password.setText("");
            return;
        }
        //Toast.makeText(view.getContext(), "Enter success", Toast.LENGTH_SHORT).show();
        notesController.addNote(_login, "Заметка", "Это интересная заметка", "C:");
        notesController.selectUserNotes(_login);
        notesController.deleteNote(_login, "Заметка", "Это интересная заметка");
        notesController.selectUserNotes(_login);

        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
