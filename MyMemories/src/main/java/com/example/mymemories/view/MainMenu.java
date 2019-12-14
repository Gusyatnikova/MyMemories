package com.example.mymemories.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mymemories.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, Edit.class);
        startActivity(intent);
    }
}
