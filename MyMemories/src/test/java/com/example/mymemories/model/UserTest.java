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
}