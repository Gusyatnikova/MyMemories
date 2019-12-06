package com.example.mymemories.model;

import java.io.File;
import java.util.ArrayList;

public class Note {
    private String title;
    private String content;
    private String date;
    private ArrayList<File> resources;

    public Note(String Title, String Content, String Date, String Resources){
        title = Title;
        content = Content;
        date = Date;
        parseResources(Resources);
    }

    //TODO парсинг строки ресурсов
    private void parseResources(String res){

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
}
