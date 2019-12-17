package com.example.mymemories.model;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class Note {
    private final UUID uuid;
    private String title;
    private String content;
    private String date;
    private ArrayList<File> resources;
    private ShortNote shortNote;

    public Note(String Title, String Content, String Date, String Resources){
        title = Title;
        content = Content;
        date = Date;
        this.uuid = UUID.randomUUID();
        shortNote = new ShortNote(title,date,uuid);
        parseResources(Resources);
    }

    /** Функция парсинга строки ресурсов
     *
     * @param res строка в виде путей к файлам ресурсов, разделенных символом "?"
     */
    private void parseResources(String res){
        String[] result = res.split("\\?");
        for(String str: result){
            if(str != null && checkIfFileExists(str))
                resources.add(new File(str));
        }
    }

    private boolean checkIfFileExists(String str){
        File file = new File(str);
        if(file.exists())
            return true;
        else return false;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<File> getResources() {
        return resources;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResources(ArrayList<File> resources) {
        this.resources = resources;
    }

    public ShortNote getShortNote() {
        return shortNote;
    }

    public UUID getUuid() {
        return uuid;
    }
}