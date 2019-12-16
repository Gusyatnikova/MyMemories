package com.example.mymemories.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymemories.R;
import com.example.mymemories.model.Note;
import com.example.mymemories.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private TextView date;
    private String day;
    private String uuid;
    Calendar dateTime= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        date = findViewById(R.id.date);
        String className = getIntent().getStringExtra("Class");
        if(className.equals("MainMenu")){
            setInitialDateTime();
        }
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        day = sdf.format(Calendar.getInstance().getTime());
        date.setText(day);
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                day = dayOfMonth + "-" + month + "-" + year;
                date.setText(day);
            }
        };
        new DatePickerDialog(Edit.this, listener,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void save(View view){
        String Title = title.getText().toString();
        String Content = content.getText().toString();
        //TODO add resources from device
        String resources="res";
        User.getUser().addNote(new Note(Title,Content,day,resources));
        Toast.makeText(view.getContext(), Title + " " + Content + " " + resources+" "+day+" added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), MainMenu.class);
        startActivity(intent);
    }
	
    public void cancel(View view){
        Intent intent = new Intent(view.getContext(),MainMenu.class);
        startActivity(intent);
    }
}
