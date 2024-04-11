package com.example.petproject.updatepart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.petproject.R;

public class CreateNewList extends AppCompatActivity {

    private ListView noteListView;
    NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);
        initWidgets();
        setNoteAdapter();

        EditText editText = findViewById(R.id.listName);
        Button button = findViewById(R.id.saveList);
                button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText.getText().toString();
                if(!name.isEmpty()){
                    PlayList playList = new PlayList(name, noteAdapter.selectedNotes);
                    PlayList.playListArrayList.add(playList);

                    startActivity(new Intent(getApplicationContext(), ListOfListsOfNotes.class));
                    finish();
                }
                else {
                    Toast.makeText(CreateNewList.this, "Create the name for the list",Toast.LENGTH_SHORT).show();

                }



            }
        });
    }



    private void initWidgets() {
        noteListView = findViewById(R.id.allListView);
    }
    private void setNoteAdapter() {
        noteAdapter = new NoteAdapter(getApplicationContext(), Note.noteArrayList);
        noteListView.setAdapter(noteAdapter);

    }

}