package com.example.petproject.updatepart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.petproject.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
    }
    public ArrayList<String> selectedNotes = new ArrayList<String>();
    public CheckBox checkBox;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_cell, parent, false);
        }
        TextView title = convertView.findViewById(R.id.noteTitle);
        TextView country = convertView.findViewById(R.id.countryTitle);
        checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ){
                    note.setChecked(isChecked);
                    selectedNotes.add(note.getTitle());
                    System.out.println(selectedNotes.toString());



                }else{note.setChecked(isChecked);
                    selectedNotes.remove(note.getTitle());
                    System.out.println(selectedNotes.toString());
                }


            }
        });



        title.setText(note.getTitle());
        country.setText(note.getCountry());
        return convertView;
    }
}
