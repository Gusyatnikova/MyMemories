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
}

