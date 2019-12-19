package com.example.mymemories.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ShortNoteTest {

    @Test
    public void getTitle() {
        ShortNote shortNote = new ShortNote("Title", "12-12-2019", UUID.randomUUID());
        assertEquals("Title", shortNote.getTitle());
    }

    @Test
    public void getDate() {
        ShortNote shortNote = new ShortNote("Title", "12-12-2019", UUID.randomUUID());
        assertEquals("12-12-2019", shortNote.getDate());
    }

    @Test
    public void getUuid() {
        UUID uuid = UUID.randomUUID();
        ShortNote shortNote = new ShortNote("Title", "12-12-2019", uuid);
        assertEquals(uuid, shortNote.getUuid());
    }
}