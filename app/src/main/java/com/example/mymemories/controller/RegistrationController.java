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

    //Функции для работы с базой данных
    private boolean doSelect(String _login, String _password){return false;}
    public void doInsert(String _login, String _password, String _email){}
    public void delete(String _login, String _password){}
}
