package com.example.mymemories.model;

import java.util.ArrayList;

public class User {
    private String login;
    private ArrayList<Note> notes;

    public User(){
        login = null;
    }

    public User(String Login){
        login = Login;
    }

    public String getLogin() {
        return login;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNotes(Note note) {
        this.notes.add(note);
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }
}
