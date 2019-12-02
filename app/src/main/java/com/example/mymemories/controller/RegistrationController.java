package com.example.mymemories.controller;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.text.TextUtils;

        import com.example.mymemories.model.AuthorizationContract.AuthorizationEntry;

public class RegistrationController {
    private AuthorizationDataBaseHelper dataBaseHelper;
    private String login;
    private String password;
    private String email;


    public RegistrationController(Context context) {
        dataBaseHelper = new AuthorizationDataBaseHelper(context);
    }

    public void registrate(String _login, String _password, String _email) {
        if(validate(_login, _password, _email))
            doInsert(_login, _password, _email);
    }

    private boolean validate(String _login, String _password, String _email) {
        if (_login.isEmpty() || _password.isEmpty() || _email.isEmpty())
            return false;
        else if(_password.length() < 6)
            return false;
        else if(!isValidEmail(_email))
            return false;
        else if(doSelect(_login, _password))
            return false;
        else return true;
    }

    private boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target)) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**Функция нахождения в базе данных е-мейла по логину и паролю
     * Используется также для проверки дублирования логинов
     *
     * @param _login
     * @param _password
     * @return
     */
    private boolean doSelect(String _login, String _password){
        String selection;
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String[] projection = {
                AuthorizationEntry.EMAIL,};
        String[] selectionArgs;
        selection = AuthorizationEntry.LOGIN + "=? AND " + AuthorizationEntry.PASSWORD + "=?";
        selectionArgs = new String[]{_login, _password};
        /*    selection = AuthorizationEntry.LOGIN + "=?";
            selectionArgs = new String[]{login};
        */
        Cursor cursor = db.query(
                AuthorizationEntry.TABLE_NAME, // таблица
                projection,            // столбцы
                selection,             // столбцы для условия WHERE
                selectionArgs,         // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                AuthorizationEntry.EMAIL + " DESC");  // порядок сортировки
        try {
            while (cursor.moveToNext()) {
                String currentEmail = cursor.getString(cursor.getColumnIndex(AuthorizationEntry.EMAIL));
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
        return false;}

    /** Функция добавления пользователя в базу данных
     *
     * @param _login
     * @param _password
     * @param _email
     */

    public void doInsert(String _login, String _password, String _email) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AuthorizationEntry.LOGIN, _login);
        values.put(AuthorizationEntry.PASSWORD, _password);
        values.put(AuthorizationEntry.EMAIL, _email);

        db.insert(AuthorizationEntry.TABLE_NAME, null, values);
    }

    /** Функция удаления пользователя из базы данных
     *
     * @param _login
     * @param _password
     */

    public void delete(String _login, String _password){
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        db.delete(AuthorizationEntry.TABLE_NAME, "Login=? and Password=?",new String[]{_login, _password});
    }
}
