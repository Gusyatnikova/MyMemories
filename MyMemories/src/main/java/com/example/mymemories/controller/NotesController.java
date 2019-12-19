package com.example.mymemories.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mymemories.model.Note;
import com.example.mymemories.model.NotesContract.NotesEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class NotesController {
    private NotesDatabaseHelper dataBaseHelper;
    private final static String LOG_TAG = "NotesController";

    public NotesController(Context context) {
        dataBaseHelper = new NotesDatabaseHelper(context);
    }

    public ArrayList<Note> selectUserNotes(String login) {
        return doSelect(login);
    }

    public void addNote(String uuid, String login, String title, String content, String date, String res) {
        doInsert(uuid, login, title, content, date, res);
    }

    public void deleteNote(String uuid) {
        delete(uuid);
    }

    private ArrayList<Note> doSelect(String login) {
        ArrayList<Note> arrayList = new ArrayList<>();
        Note note;
        String selection;
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String[] projection = {
                NotesEntry.UUID, NotesEntry.DATE, NotesEntry.TITLE, NotesEntry.CONTENT, NotesEntry.RESOURCES};
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
                String currentUUID = cursor.getString(cursor.getColumnIndex(NotesEntry.UUID));
                String currentDate = cursor.getString(cursor.getColumnIndex(NotesEntry.DATE));
                String currentTitle = cursor.getString(cursor.getColumnIndex(NotesEntry.TITLE));
                String currentContent = cursor.getString(cursor.getColumnIndex(NotesEntry.CONTENT));
                String currentResources = cursor.getString(cursor.getColumnIndex(NotesEntry.RESOURCES));
                note = new Note(currentTitle, currentContent, currentDate, currentResources, currentUUID);
                arrayList.add(note);
            }
        } finally {
            cursor.close();
        }
        return arrayList;
    }

    /**
     * Функция добавления записки пользователя в базу данных
     *
     * @param uuid
     * @param login
     * @param title
     * @param content
     * @param resources
     */

    private void doInsert(String uuid, String login, String title, String content, String date, String resources) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        //String currentDay = sdf.format(Calendar.getInstance().getTime());

        ContentValues values = new ContentValues();
        values.put(NotesEntry.UUID, uuid);
        values.put(NotesEntry.LOGIN, login);
        values.put(NotesEntry.DATE, date);
        values.put(NotesEntry.TITLE, title);
        values.put(NotesEntry.CONTENT, content);
        values.put(NotesEntry.RESOURCES, resources);

        db.insert(NotesEntry.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "added");
    }

    /**
     * Функция удаления записки пользователя из базы данных
     *
     * @param uuid
     */

    private void delete(String uuid) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        db.delete(NotesEntry.TABLE_NAME, "UUID=?", new String[]{uuid});
        Log.d(LOG_TAG, "deleted");
    }
}