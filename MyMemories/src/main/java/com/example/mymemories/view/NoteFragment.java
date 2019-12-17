package com.example.mymemories.view;

import android.os.Bundle;
import android.widget.SimpleAdapter;
import com.example.mymemories.R;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.fragment.app.ListFragment;

public class NoteFragment extends ListFragment {
    private ArrayList<HashMap<String, String>> notes = new ArrayList<>();
    private HashMap<String, String> map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNotes();
        SimpleAdapter adapter = new SimpleAdapter(this.getContext(), notes,
                R.layout.note_fragment,
                new String[]{"Title", "Date", "UUID"},
                new int[]{R.id.title, R.id.date,
                        R.id.uuid});
        setListAdapter(adapter);
    }

    private void setNotes(){
        //TODO
    }
}