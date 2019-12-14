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

    public ArrayList<Note> selectUserNotes(String login){
        return doSelect(login);
    }

    public void addNote(String login, String title, String content, String res){
        doInsert(login, title, content, res);
    }

    public void deleteNote(String login, String title, String content){
        delete(login, title, content);
    }

    private ArrayList<Note> doSelect(String login){
        ArrayList<Note> arrayList = new ArrayList<>();
        Note note;
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
                note = new Note(currentTitle,currentContent,currentDate,currentResources);
                arrayList.add(note);
            }
        } finally {
            cursor.close();
        }
        return arrayList;
    }

    /** Функция добавления записки пользователя в базу данных
     *
     * @param login
     * @param title
     * @param content
     * @param resources
     */

    private void doInsert(String login, String title, String content, String resources) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDay = sdf.format(Calendar.getInstance().getTime());

        ContentValues values = new ContentValues();
        values.put(NotesEntry.LOGIN, login);
        values.put(NotesEntry.DATE, currentDay);
        values.put(NotesEntry.TITLE, title);
        values.put(NotesEntry.CONTENT, content);
        values.put(NotesEntry.RESOURCES, resources);

        db.insert(NotesEntry.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "added");
    }

    /** Функция удаления записки пользователя из базы данных
     *
     * @param login
     * @param title
     * @param content
     */

    private void delete(String login, String title, String content){
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        db.delete(NotesEntry.TABLE_NAME, "Login=? and Title=? and Content=?",new String[]{login, title, content});
        Log.d(LOG_TAG, "deleted");
    }
}
