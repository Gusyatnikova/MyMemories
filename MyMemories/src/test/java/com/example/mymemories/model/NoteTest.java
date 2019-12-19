package com.example.mymemories.model;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class NoteTest {

    @Test
    public void getTitle() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String title = note.getTitle();
        assertEquals(title,"Title");
    }

    @Test
    public void getContent() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String content = note.getContent();
        assertEquals(content,"Content");
    }

    @Test
    public void getDate() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String date = note.getDate();
        assertEquals(date,"12-12-2019");
    }

    @Test
    public void getResources() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String resources = note.getResources().get(0).toString();
        assertEquals(resources,"C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
    }

    @Test
    public void setTitle() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String newTitle = "NewTitle";
        note.setTitle(newTitle);
        assertEquals(newTitle, note.getTitle());
    }

    @Test
    public void setContent() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String newContent = "NewContent";
        note.setContent(newContent);
        assertEquals(newContent, note.getContent());
    }

    @Test
    public void setDate() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String newDate = "13-12-2019";
        note.setDate(newDate);
        assertEquals(newDate, note.getDate());
    }

    @Test
    public void setResources() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String newRes = "C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png";
        ArrayList<File> newResources = new ArrayList<>();
        newResources.add(new File(newRes));
        assertEquals(newRes, newResources.get(0).getPath());
    }

    @Test
    public void getShortNote() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String title = "Title";
        ShortNote shortNote = note.getShortNote();
        assertEquals(title, shortNote.getTitle());
    }

    @Test
    public void getUuid() {
        Note note = new Note("Title","Content","12-12-2019","C:\\Users\\Ann\\AndroidProjects\\MyMemories\\MyMemories\\src\\main\\res\\drawable\\galery.png");
        String uuid = note.getUuid().toString();
        String uuidShort = note.getShortNote().getUuid().toString();
        assertEquals(uuid, uuidShort);
    }
}