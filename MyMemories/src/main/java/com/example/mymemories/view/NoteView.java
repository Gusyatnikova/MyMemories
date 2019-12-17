package com.example.mymemories.view;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.example.mymemories.R;
        import com.example.mymemories.model.User;

        import androidx.appcompat.app.AppCompatActivity;

public class NoteView extends AppCompatActivity {
    TextView title;
    TextView date;
    EditText content;
    String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        content = findViewById(R.id.content);
        initValues();
    }

    public void initValues(){
        //TODO
    }
}