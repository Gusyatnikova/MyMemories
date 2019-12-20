package com.example.mymemories.controller;

import android.content.Context;
import com.example.mymemories.model.Note;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import androidx.test.platform.app.InstrumentationRegistry;
import static org.junit.Assert.assertEquals;

public class NotesControllerTest {

    String uuid;

    @After
    public void deletesNote(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        NotesController controller = new NotesController(appContext);
        controller.deleteNote(uuid);
    }

    @Test
    public void selectUserNotes() {
        String login = "Ann";
        String title = "Заметка";
        String content = "Заметка";
        String resources = "C:file";
        String date = "12-12-2019";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        NotesController controller = new NotesController(appContext);
        Note note = new Note(title,content,date,resources);
        uuid = note.getUuid().toString();
        controller.addNote(uuid, login, title, content, date, resources);
        ArrayList<Note> notes = controller.selectUserNotes(login);
        assertEquals("Заметка", notes.get(0).getTitle());
    }
}