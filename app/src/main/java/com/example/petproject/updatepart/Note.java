package com.example.petproject.updatepart;

import java.util.ArrayList;

public class Note {
    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    private int id;
    private String title;
    private String content;
    private String country;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public Note(int id, String title, String content, String country) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.country = country;
    }


    public Note(int id, String title, String content, String country, boolean checked) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.country = country;
        this.checked = checked;
    }

    public Note(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
