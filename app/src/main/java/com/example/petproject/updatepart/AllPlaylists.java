package com.example.petproject.updatepart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.petproject.CommentsAdapter;
import com.example.petproject.R;

public class AllPlaylists extends AppCompatActivity {


    private ListView noteListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createNotes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_playlists);
        initWidgets();
        setNoteAdapter();

//        Button button = findViewById(R.id.playListButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ListOfListsOfNotes.class));
//                finish();
//
//            }
//        });
    }



    private void initWidgets() {
        noteListView = findViewById(R.id.allListView);
    }
    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.noteArrayList);
        noteListView.setAdapter(noteAdapter);

    }
    public void createNotes(){
        Note note1 = new Note(1,"Best trip","Content", "France");
        Note note2 = new Note(2,"Medium Trip","Content", "Germany");
        Note note3 = new Note(3,"Baza","Content", "France");
        Note note4 = new Note(4,"Example","Content", "Ukraine");
        Note note5 = new Note(5,"Love","Content", "Turkey");
        Note note6 = new Note(6,"Rock","Content", "USA");
        Note note7 = new Note(7,"Mission","Content", "France");
//        Note note8 = new Note(8,"Work trip","Content", "Poland");
//        Note note9 = new Note(9,"Studies1","Content", "Lithuania");
//        Note note10 = new Note(10,"Erasmus","Content", "Czech republic");

        Note.noteArrayList.add(note1);
        Note.noteArrayList.add(note2);
        Note.noteArrayList.add(note3);
        Note.noteArrayList.add(note4);
        Note.noteArrayList.add(note5);
        Note.noteArrayList.add(note6);
        Note.noteArrayList.add(note7);
//        Note.noteArrayList.add(note8);
//        Note.noteArrayList.add(note9);
//        Note.noteArrayList.add(note10);



    }
}