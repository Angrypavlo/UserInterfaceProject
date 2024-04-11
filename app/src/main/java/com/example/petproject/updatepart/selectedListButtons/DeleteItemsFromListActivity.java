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

public class DeleteItemsFromListActivity extends AppCompatActivity {

    private ListView noteListView;
    NoteAdapter noteAdapter;
    ArrayList<Note> itemsToDelete = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_items_from_list);
        initWidgets();
        setNoteAdapter();

        Button button = findViewById(R.id.deleteSelected);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSelectedItems();
                SelectedPlayList.selectedNotesFromList = new ArrayList<>();
                startActivity(new Intent(getApplicationContext(), SelectedPlayList.class));
                finish();

            }
        });
    }



    private void initWidgets() {
        noteListView = findViewById(R.id.allDeletedListView);
    }
    private void setNoteAdapter() {
        noteAdapter = new NoteAdapter(getApplicationContext(), SelectedPlayList.selectedNotesFromList);
        noteListView.setAdapter(noteAdapter);

    }
    private void deleteSelectedItems(){
        PlayList.playListArrayList.remove(ListOfListsOfNotes.selectedPlayList);

//        ArrayList<Note> itemsToDelete = new ArrayList<>();
//        for(int i = SelectedPlayList.selectedNotesFromList.size()-1; i>=0;i--){
//            for(int a = noteAdapter.selectedNotes.size()-1; a>=0;a--){
//                if(SelectedPlayList.selectedNotesFromList.get(i).getTitle()!=noteAdapter.selectedNotes.get(a)){
//                    itemsToDelete.add(SelectedPlayList.selectedNotesFromList.get(i));
//                }
//            }
//        }
        itemsToDelete = SelectedPlayList.selectedNotesFromList;


        ArrayList<String> newPlaylist = new ArrayList<>();
        for(int n = itemsToDelete.size()-1;n>=0;n--){
            newPlaylist.add(itemsToDelete.get(n).getTitle());
        }
        newPlaylist.removeAll(noteAdapter.selectedNotes);
        ListOfListsOfNotes.selectedPlayList = new PlayList(ListOfListsOfNotes.selectedPlayList.getTitle(),newPlaylist);
        PlayList.playListArrayList.add(ListOfListsOfNotes.selectedPlayList);
    }
}