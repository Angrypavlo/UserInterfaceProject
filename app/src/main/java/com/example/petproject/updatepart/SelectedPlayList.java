package com.example.petproject.updatepart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.petproject.R;
import com.example.petproject.updatepart.selectedListButtons.DeleteItemsFromListActivity;
import com.example.petproject.updatepart.selectedListButtons.EditListActivity;

import java.util.ArrayList;

public class SelectedPlayList extends AppCompatActivity {

    private ListView noteListView;
    public static ArrayList<Note> selectedNotesFromList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getAllNotes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_play_list);
        initWidgets();
        setNoteAdapter();
        System.out.println("name:"+ ListOfListsOfNotes.selectedPlayList.getTitle() + "Collection of notes"+ ListOfListsOfNotes.selectedPlayList.getCollectionOfNotes());

        Button button1 = findViewById(R.id.playListButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNotesFromList = new ArrayList<>();
                startActivity(new Intent(getApplicationContext(), ListOfListsOfNotes.class));
                finish();
            }
        });
        Button button2 = findViewById(R.id.editPlaylist);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditListActivity.class));
                finish();
            }
        });
        Button button3 = findViewById(R.id.deleteItemsFromListButton);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DeleteItemsFromListActivity.class));
                finish();
            }
        });
        Button button4 = findViewById(R.id.deleteListButton);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedNotesFromList = new ArrayList<>();
                PlayList.playListArrayList.remove(ListOfListsOfNotes.selectedPlayList);

                //NoteAdapter.selectedNotes.clear();
                ListOfListsOfNotes.selectedPlayList = new PlayList();

                startActivity(new Intent(getApplicationContext(), ListOfListsOfNotes.class));
                finish();
            }
        });
//i love you
    }

    private void initWidgets() {
        noteListView = findViewById(R.id.allBestListView);
    }
    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), selectedNotesFromList);
        noteListView.setAdapter(noteAdapter);

    }
    private void getAllNotes(){
        for(int i = Note.noteArrayList.size()-1; i>=0; i--){
            for (int a = ListOfListsOfNotes.selectedPlayList.getCollectionOfNotes().size()-1; a>=0;a--){
                if(Note.noteArrayList.get(i).getTitle()==ListOfListsOfNotes.selectedPlayList.getCollectionOfNotes().get(a)){
                    selectedNotesFromList.add(Note.noteArrayList.get(i));
                }
                else continue;
            }
        }
    }
}