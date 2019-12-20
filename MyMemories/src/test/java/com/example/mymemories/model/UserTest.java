package com.example.mymemories.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getLogin() {
        String login = "Ann";
        User.getUser().setLogin(login);
        assertEquals(login,User.getUser().getLogin());
    }

    @Test
    public void getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("Title","Content","12-12-2019","C:" ));
        User.getUser().setNotes(notes);
        assertEquals("Title",User.getUser().getNotes().get(0).getTitle());
    }

    @Test
    public void setLogin() {
        String login = "Ann";
        User.getUser().setLogin(login);
        assertEquals(login,User.getUser().getLogin());
    }

    @Test
    public void addNote() {
        Note note = new Note("Title","Content","12-12-2019","C:");
        User.getUser().addNote(note);
        assertEquals("Content",User.getUser().getContent(note.getUuid().toString()));
    }

    @Test
    public void setPassword() {
        String password = "12345678";
        User.getUser().setPassword(password);
        assertEquals(password,User.getUser().getPassword());
    }

    @Test
    public void setEmail() {
        String email = "asd@gmail.com";
        User.getUser().setEmail(email);
        assertEquals(email,User.getUser().getEmail());
    }

    @Test
    public void setNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("Title", "Note", "15-12-2019","C:/?"));
        User.getUser().setNotes(notes);
        assertEquals(notes.get(0).getTitle(),User.getUser().getNotes().get(0).getTitle());
    }

    @Test
    public void getShortNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("Title", "Note", "15-12-2019","C:/?"));
        User.getUser().setNotes(notes);
        ArrayList<ShortNote> shortNotes = User.getUser().getShortNotes();
        assertEquals(notes.get(0).getShortNote().getTitle(),shortNotes.get(0).getTitle());
    }

    @Test
    public void getContent() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("Title", "Note", "15-12-2019","C:/?"));
        User.getUser().setNotes(notes);
        String uuid = notes.get(0).getUuid().toString();
        assertEquals(notes.get(0).getContent(),User.getUser().getContent(uuid));
    }

    @Test
    public void deleteNote() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("Title", "Note", "15-12-2019","C:/?"));
        notes.add(new Note("Title", "Note!", "16-12-2019","C:/?"));
        User.getUser().setNotes(notes);
        String uuid = notes.get(0).getUuid().toString();
        User.getUser().deleteNote(uuid);
        assertEquals(1, User.getUser().getNotes().size());
    }

    @Test
    public void changeNote() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("Title", "Note", "15-12-2019","C:/?"));
        User.getUser().setNotes(notes);
        String uuid = notes.get(0).getUuid().toString();
        User.getUser().changeNote("NewTitle", "16-12-2019","Other content" , "C:/?",uuid);
        assertEquals("NewTitle",User.getUser().getNotes().get(0).getTitle());
    }
}