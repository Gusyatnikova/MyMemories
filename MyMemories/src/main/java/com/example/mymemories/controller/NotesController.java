package com.example.mymemories.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mymemories.model.NotesContract.NotesEntry;

import java.util.Calendar;
import java.util.Date;

public class NotesController {
    private NotesDatabaseHelper dataBaseHelper;
    private final static String LOG_TAG = "NotesController";

    public NotesController(Context context) {
        dataBaseHelper = new NotesDatabaseHelper(context);
    }

    public void selectUserNotes(String login){
        doSelect(login);
    }

    private void doSelect(String login){
        String selection;
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String[] projection = {
                NotesEntry.DATE, NotesEntry.TITLE, NotesEntry.CONTENT, NotesEntry.RESOURCES};
        String[] selectionArgs;
        selection = NotesEntry.LOGIN + "=?";
        selectionArgs = new String[]{login};
        Cursor cursor = db.query(
                NotesEntry.TABLE_NAME, // таблица
                projection,            // столбцы
                selection,             // столбцы для условия WHERE
                selectionArgs,         // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                NotesEntry.DATE + " ASC");  // порядок сортировки
        try {
            while (cursor.moveToNext()) {
                String currentDate = cursor.getString(cursor.getColumnIndex(NotesEntry.DATE));
                String currentTitle = cursor.getString(cursor.getColumnIndex(NotesEntry.TITLE));
                String currentContent = cursor.getString(cursor.getColumnIndex(NotesEntry.CONTENT));
                String currentResources = cursor.getString(cursor.getColumnIndex(NotesEntry.RESOURCES));
                Log.d(LOG_TAG, currentDate);
                Log.d(LOG_TAG, currentTitle);
                Log.d(LOG_TAG, currentContent);
                Log.d(LOG_TAG, currentResources);
            }
        } finally {
            cursor.close();
        }
    }
}
