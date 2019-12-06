package com.example.mymemories.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mymemories.R;
import com.example.mymemories.controller.NotesController;

public class MainMenu extends AppCompatActivity {

    private NotesController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        controller = new NotesController(this.getApplicationContext());
    }
}
