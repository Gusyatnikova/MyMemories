package com.example.mymemories.model;

import java.util.ArrayList;

public class User {
    private static User user;
    private String login;
    private String password;
    private String email;
    private ArrayList<Note> notes;

    private User(){
    }

    public static User getUser(){
        if(user == null)
            user = new User();
        return user;
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

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<ShortNote> getShortNotes() {
        ArrayList<ShortNote> shortNotes = new ArrayList<>();
        for (Note note: notes) {
            shortNotes.add(note.getShortNote());
        }
        return shortNotes;
    }

    public String getContent(String uuid){
        for(Note note : notes){
            if(note.getUuid().toString().equals(uuid)){
                return note.getContent();
            }
        }
        return "";
    }

    public void deleteNote(String uuid){
        for(Note note : notes){
            if(note.getUuid().toString().equals(uuid))
                notes.remove(note);
        }
    }

    public void changeNote(String Title, String Date, String Content, String res, String uuid){
        for(Note note : notes){
            if(note.getUuid().toString().equals(uuid)){
                notes.remove(note);
                notes.add(new Note(Title,Content,Date,res,uuid));
            }
        }
    }
}