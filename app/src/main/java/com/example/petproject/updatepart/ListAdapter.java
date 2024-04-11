package com.example.petproject.updatepart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.petproject.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<PlayList> {
    public ListAdapter(Context context, List<PlayList> playLists) {
        super(context, 0, playLists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlayList playList = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.playlist_cell, parent, false);
        }
        TextView title = convertView.findViewById(R.id.playlistTitle);
        TextView subTitle1 = convertView.findViewById(R.id.note1Title);
        TextView subTitle2 = convertView.findViewById(R.id.note2Title);
        TextView subTitle3 = convertView.findViewById(R.id.note3Title);


        title.setText(playList.getTitle());

        switch (playList.getCollectionOfNotes().size()){
            case 0:
                subTitle1.setVisibility(View.INVISIBLE);
                subTitle2.setVisibility(View.INVISIBLE);
                subTitle3.setVisibility(View.INVISIBLE);
                break;
            case 1:
                subTitle1.setText(String.valueOf(playList.getCollectionOfNotes().get(0)));
                subTitle2.setVisibility(View.INVISIBLE);
                subTitle3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                subTitle1.setText(String.valueOf(playList.getCollectionOfNotes().get(0)));
                subTitle2.setText(String.valueOf(playList.getCollectionOfNotes().get(1)));
                subTitle3.setVisibility(View.INVISIBLE);
                break;
            default:
                subTitle1.setText(String.valueOf(playList.getCollectionOfNotes().get(0)));
                subTitle2.setText(String.valueOf(playList.getCollectionOfNotes().get(1)));
                subTitle3.setText(String.valueOf(playList.getCollectionOfNotes().get(2)));
                break;

        }
        return convertView;
    }
}
