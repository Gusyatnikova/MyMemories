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
        String Title = getIntent().getStringExtra("Title");
        String Date = getIntent().getStringExtra("Date");
        uuid = getIntent().getStringExtra("UUID");
        title.setText(Title);
        date.setText(Date);
        content.setText(User.getUser().getContent(uuid));
    }

    public void delete(View view){
        User.getUser().deleteNote(uuid);
        Intent intent = new Intent(this.getApplicationContext(),MainMenu.class);
        startActivity(intent);
    }

    public void edit(View view){
        Intent intent = new Intent(this.getApplicationContext(), Edit.class);
        intent.putExtra("Class","NoteView");
        intent.putExtra("Title",title.getText());
        intent.putExtra("Date",date.getText());
        intent.putExtra("Content",content.getText().toString());
        intent.putExtra("UUID",uuid);
        startActivity(intent);
    }
}