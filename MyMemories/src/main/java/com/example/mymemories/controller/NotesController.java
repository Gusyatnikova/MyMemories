package com.example.mymemories.controller;

import android.content.Context;

public class NotesController {
    private NotesDatabaseHelper dataBaseHelper;

    public NotesController(Context context) {
        dataBaseHelper = new NotesDatabaseHelper(context);
    }
}
