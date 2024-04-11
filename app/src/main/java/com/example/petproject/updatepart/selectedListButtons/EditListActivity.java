package com.example.petproject.updatepart.selectedListButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.petproject.R;
import com.example.petproject.updatepart.ListOfListsOfNotes;
import com.example.petproject.updatepart.Note;
import com.example.petproject.updatepart.NoteAdapter;
import com.example.petproject.updatepart.PlayList;
import com.example.petproject.updatepart.SelectedPlayList;

import java.util.ArrayList;

public class EditListActivity extends AppCompatActivity {

    private ListView noteListView;
    NoteAdapter noteAdapter;
    ArrayList<Note> itemsNotInSelectedList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createListWithoutAllItems();
        setContentView(R.layout.activity_edit_list);
        initWidgets();
        setNoteAdapter();

        Button button = findViewById(R.id.addItems);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSelectedItems();
                SelectedPlayList.selectedNotesFromList = new ArrayList<>();
                startActivity(new Intent(getApplicationContext(), SelectedPlayList.class));
                finish();

            }
        });
    }



    private void initWidgets() {
        noteListView = findViewById(R.id.allListViewUpdates);
    }
    private void setNoteAdapter() {

        noteAdapter = new NoteAdapter(getApplicationContext(), itemsNotInSelectedList);
        noteListView.setAdapter(noteAdapter);

    }

    public void createListWithoutAllItems(){

        for(int i = Note.noteArrayList.size()-1; i>=0; i--){
            String value = Note.noteArrayList.get(i).getTitle();
            if(!ListOfListsOfNotes.selectedPlayList.getCollectionOfNotes().contains(value)){
                itemsNotInSelectedList.add(Note.noteArrayList.get(i));
            }
            else continue;
        }

    }
    private void addSelectedItems(){
        PlayList.playListArrayList.remove(ListOfListsOfNotes.selectedPlayList);

        ArrayList<String> itemsToAdd = new ArrayList<>();
        for(int i = itemsNotInSelectedList.size()-1; i>=0;i--){
            for(int a = noteAdapter.selectedNotes.size()-1; a>=0;a--){
                if(itemsNotInSelectedList.get(i).getTitle()==noteAdapter.selectedNotes.get(a)){
                    itemsToAdd.add(itemsNotInSelectedList.get(i).getTitle());
                }
            }
        }
        itemsToAdd.addAll(ListOfListsOfNotes.selectedPlayList.getCollectionOfNotes());

        PlayList renewedPlaylist = new PlayList(ListOfListsOfNotes.selectedPlayList.getTitle(),itemsToAdd);
        ListOfListsOfNotes.selectedPlayList = renewedPlaylist;
        PlayList.playListArrayList.add(ListOfListsOfNotes.selectedPlayList);
    }
}