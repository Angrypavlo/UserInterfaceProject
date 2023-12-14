package com.example.petproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AllCommentsResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_comments_dialog);

        // Retrieve the selected comment from the intent
        Comment selectedComment = getIntent().getParcelableExtra("selectedComment");

        // Use the selectedComment as needed in your layout
    }
}
