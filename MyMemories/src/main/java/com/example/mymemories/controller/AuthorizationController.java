package com.example.mymemories.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mymemories.model.AuthorizationContract;
import com.example.mymemories.model.User;

public class AuthorizationController {
    private AuthorizationDataBaseHelper dataBaseHelper;
    private NotesController notesController;
    private String login;
    private String password;
    private String email;

    public AuthorizationController(Context context) {
        dataBaseHelper = new AuthorizationDataBaseHelper(context);
        notesController = new NotesController(context);
    }

    public void enter(String _login, String _password) throws CustomException {
        validate(_login, _password);
        User.getUser().setLogin(login);
        User.getUser().setPassword(password);
        User.getUser().setEmail(email);
        User.getUser().setNotes(notesController.selectUserNotes(User.getUser().getLogin()));
    }

    private void validate(String _login, String _password) throws CustomException {
        if (_login.isEmpty() || _password.isEmpty())
            throw new CustomException("Fields mustn't be empty");
        else if (!doSelect(_login, _password))
            throw new CustomException("Wrong login or password");
    }

    private boolean doSelect(String _login, String _password) {
        String selection;
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String[] projection = {
                AuthorizationContract.AuthorizationEntry.EMAIL,};
        String[] selectionArgs;
        selection = AuthorizationContract.AuthorizationEntry.LOGIN + "=? AND " + AuthorizationContract.AuthorizationEntry.PASSWORD + "=?";
        selectionArgs = new String[]{_login, _password};
        Cursor cursor = db.query(
                AuthorizationContract.AuthorizationEntry.TABLE_NAME, // таблица
                projection,            // столбцы
                selection,             // столбцы для условия WHERE
                selectionArgs,         // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                AuthorizationContract.AuthorizationEntry.EMAIL + " DESC");  // порядок сортировки
        try {
            while (cursor.moveToNext()) {
                String currentEmail = cursor.getString(cursor.getColumnIndex(AuthorizationContract.AuthorizationEntry.EMAIL));
                if (currentEmail.isEmpty())
                    return false;
                else {
                    login = _login;
                    password = _password;
                    email = currentEmail;
                    return true;
                }
            }
        } finally {
            cursor.close();
        }
        return false;
    }

    public String getEmail() {
        return email;
    }
}
