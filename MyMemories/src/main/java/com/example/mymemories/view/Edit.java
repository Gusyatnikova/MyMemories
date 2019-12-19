package com.example.mymemories.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
    private String className;
    private EditText resources;
    private StringBuilder res;
    Calendar dateTime = Calendar.getInstance();
    static final int RESOURCES_REQUEST = 1;
    private final static String LOG_TAG = "Edit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        date = findViewById(R.id.date);
        resources = findViewById(R.id.resources);
        res = new StringBuilder();
        className = getIntent().getStringExtra("Class");
        if (className.equals("MainMenu")) {
            setInitialDateTime();
        } else {
            String Title = getIntent().getStringExtra("Title");
            String Date = getIntent().getStringExtra("Date");
            String Content = getIntent().getStringExtra("Content");
            uuid = getIntent().getStringExtra("UUID");
            res.append(getIntent().getStringExtra("Resources"));
            resources.setText(res.toString());
            title.setText(Title);
            date.setText(Date);
            day = Date;
            content.setText(Content);
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

    public void save(View view) {
        String Title = title.getText().toString();
        String Content = content.getText().toString();
        String Resources = resources.getText().toString();
        if (className.equals("MainMenu")) {
            User.getUser().addNote(new Note(Title, Content, day, Resources));
            Toast.makeText(view.getContext(), Title + " " + Content + " " + Resources + " " + day + " added", Toast.LENGTH_SHORT).show();
        } else {
            User.getUser().changeNote(Title, day, Content, Resources, uuid);
            Toast.makeText(view.getContext(), Title + " " + Content + " " + Resources + " " + day + " changed", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(view.getContext(), MainMenu.class);
        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(view.getContext(), MainMenu.class);
        startActivity(intent);
    }

    public void addPicture(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESOURCES_REQUEST);
    }

    public void addVideo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESOURCES_REQUEST);
    }

    public void addMusic(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESOURCES_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
        super.onActivityResult(requestCode, resultCode, returnedIntent);
        if (resultCode == RESULT_OK) {
            Uri selectedRes = returnedIntent.getData();
            if (selectedRes != null) {
                res.append(selectedRes.toString());
                res.append("?");
                resources.setText(res.toString());
                //Log.i(LOG_TAG,res.toString());
                Toast.makeText(this.getApplicationContext(), resources.getText().toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getApplicationContext(), "Nothing was selected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


