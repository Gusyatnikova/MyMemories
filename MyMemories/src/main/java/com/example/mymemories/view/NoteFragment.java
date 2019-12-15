package com.example.mymemories.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mymemories.model.ShortNote;
import com.example.mymemories.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.ListFragment;

public class NoteFragment extends ListFragment {
    private static List<ShortNote> notes = new ArrayList<ShortNote>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notes = User.getUser().getShortNotes();
        ArrayAdapter<ShortNote> adapter = new NoteAdapter(this.getContext());
        setListAdapter(adapter);
    }

    private class NoteAdapter extends ArrayAdapter<ShortNote> {

        public NoteAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_2, notes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ShortNote shortNote = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(android.R.layout.simple_list_item_2, null);
            }
            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(shortNote.title);
            ((TextView) convertView.findViewById(android.R.id.text2))
                    .setText(shortNote.date);
            return convertView;
        }
    }
}