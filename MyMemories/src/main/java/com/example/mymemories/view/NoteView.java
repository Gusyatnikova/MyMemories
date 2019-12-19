package com.example.mymemories.view;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.MediaController;
        import android.widget.TextView;
        import android.widget.VideoView;

        import com.example.mymemories.R;
        import com.example.mymemories.model.User;

        import java.io.IOException;
        import java.util.ArrayList;

        import androidx.appcompat.app.AppCompatActivity;

public class NoteView extends AppCompatActivity {
    TextView title;
    TextView date;
    TextView Uuid;
    EditText content;
    LinearLayout gallery;
    LinearLayout videoGallery;
    String uuid;
    ArrayList<String> res;
    MediaPlayer audioPlayer;
    ImageButton play, pause, stop, prev, next;
    ArrayList<String> audios;
    int iterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        Uuid = findViewById(R.id.uuid);
        content = findViewById(R.id.content);
        res = new ArrayList<>();
        gallery = findViewById(R.id.gallery);
        videoGallery = findViewById(R.id.video_gallery);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        pause.setEnabled(false);
        stop.setEnabled(false);
        initValues();
    }

    public void initValues(){
        String Title = getIntent().getStringExtra("Title");
        String Date = getIntent().getStringExtra("Date");
        uuid = getIntent().getStringExtra("UUID");
        Uuid.setText(uuid);
        title.setText(Title);
        date.setText(Date);
        content.setText(User.getUser().getContent(uuid));
        res = User.getUser().getResString(uuid);
        ArrayList<String> images = getResources(uuid, "image");
        if (images.size() != 0) {
            for (String img : images) {
                gallery.addView(insertImage(img));
            }
        }
        ArrayList<String> videos = getResources(uuid, "video");
        if (videos.size() != 0) {
            for (String video : videos) {
                videoGallery.addView(insertVideo(video));
            }
        }
        audios = getResources(uuid, "audio");
        if (audios.size() != 0) {
            iterator = 0;
            audioPlayer = MediaPlayer.create(this.getApplicationContext(), Uri.parse(audios.get(iterator)));
            audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlay();
                }
            });
            play.setEnabled(true);
        } else {
            play.setEnabled(false);
            pause.setEnabled(false);
            stop.setEnabled(false);
            next.setEnabled(false);
            prev.setEnabled(false);
        }
    }

    public ArrayList<String> getResources(String uuid, String cont){
        ArrayList<String> resources = User.getUser().getResString(uuid);
        ArrayList<String> result = new ArrayList<>();
        for (String str : resources) {
            if (str.contains(cont)) {
                result.add(str);
            }
        }
        return result;
    }

    public View insertImage(String uri){
        Bitmap bitmap = null;
        LinearLayout layout = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(uri));
            layout = new LinearLayout(getApplicationContext());
            layout.setLayoutParams(new LinearLayout.LayoutParams(350, 350));
            layout.setGravity(Gravity.CENTER);

            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(345, 345));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageBitmap(bitmap);

            layout.addView(imageView);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return layout;
    }

    public View insertVideo(String uri){
        LinearLayout layout = null;
        layout = new LinearLayout(getApplicationContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(350, 350));
        layout.setGravity(Gravity.CENTER);

        VideoView videoPlay = new VideoView(getApplicationContext());
        videoPlay.setVideoURI(Uri.parse(uri));
        videoPlay.setMediaController(new MediaController(this));
        videoPlay.setOnCompletionListener(myVideoViewCompletionListener);
        videoPlay.setOnPreparedListener(MyVideoViewPreparedListener);
        videoPlay.setOnErrorListener(myVideoViewErrorListener);
        videoPlay.setLayoutParams(new LinearLayout.LayoutParams(345, 345));
        videoPlay.requestFocus();
        videoPlay.start();
        layout.addView(videoPlay);
        return layout;
    }

    private void stopPlay(){
        //TODO
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