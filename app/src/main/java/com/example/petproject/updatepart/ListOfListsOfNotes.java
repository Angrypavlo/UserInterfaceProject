package com.example.petproject.updatepart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.petproject.ListOfCountriesActivity;
import com.example.petproject.R;

import java.util.ArrayList;

public class ListOfListsOfNotes extends AppCompatActivity {

    public static PlayList selectedPlayList;
    private ListView playListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        createPlaylists();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_lists_of_notes);
        playListView = findViewById(R.id.allBestListView);
        ListAdapter noteAdapter = new ListAdapter(getApplicationContext(), PlayList.playListArrayList);
        playListView.setAdapter(noteAdapter);
        Button button = findViewById(R.id.playListButton);
        if(selectedPlayList != null){
            System.out.println("name:"+ selectedPlayList.getTitle() + "Collection of notes"+ selectedPlayList.getCollectionOfNotes());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateNewList.class));
                finish();
            }
        });
        Button backToHomeButton = findViewById(R.id.backToHomeScreen);
        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note.noteArrayList = new ArrayList<>();
                startActivity(new Intent(getApplicationContext(), ListOfCountriesActivity.class));
                finish();
            }
        });
        playListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPlayList = PlayList.playListArrayList.get(i);
                //Toast.makeText(ListOfListsOfNotes.this, "You clicked"+ PlayList.playListArrayList.get(i).getTitle(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SelectedPlayList.class));
                finish();
            }
        });

    }

}