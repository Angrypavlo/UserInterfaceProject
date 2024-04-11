package com.example.petproject.updatepart;

import java.util.ArrayList;

public class PlayList {
    private String title;


    private ArrayList<String> collectionOfNotes;
    public static ArrayList<PlayList> playListArrayList = new ArrayList<>();

    public PlayList(String title, ArrayList<String> collectionOfNotes) {
        this.title = title;
        this.collectionOfNotes = collectionOfNotes;
    }

    public PlayList(String title) {
        this.title = title;
    }

    public PlayList() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getCollectionOfNotes() {
        return collectionOfNotes;
    }

    public void setCollectionOfNotes(ArrayList<String> collectionOfNotes) {
        this.collectionOfNotes = collectionOfNotes;
    }
}
