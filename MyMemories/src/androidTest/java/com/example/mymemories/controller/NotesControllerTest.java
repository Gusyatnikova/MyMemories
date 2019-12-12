package com.example.mymemories.controller;

import android.content.Context;
import com.example.mymemories.model.Note;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import androidx.test.platform.app.InstrumentationRegistry;
import static org.junit.Assert.assertEquals;

public class NotesControllerTest {

    @After
    public void deletesNote(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        NotesController controller = new NotesController(appContext);
        controller.deleteNote("Ann", "Заметка", "Заметка");
    }

    @Test
    public void selectUserNotes() {
        String login = "Ann";
        String title = "Заметка";
        String content = "Заметка";
        String resources = "C:file";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        NotesController controller = new NotesController(appContext);
        controller.addNote(login, title, content, resources);
        ArrayList<Note> notes = controller.selectUserNotes(login);
        assertEquals("Заметка", notes.get(0).getTitle());
    }
}