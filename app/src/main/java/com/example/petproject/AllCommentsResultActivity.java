package com.example.petproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AllCommentsResultActivity extends AppCompatActivity {

    private EditText editTextComment;
    private Comment selectedComment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_comments_dialog);

        // Retrieve the selected comment from the intent
        selectedComment = getIntent().getParcelableExtra("selectedComment");

        editTextComment = findViewById(R.id.editTextUpdatedComment);
        Button saveButton = findViewById(R.id.buttonSave);

        // Set the existing comment text in the EditText
        editTextComment.setText(selectedComment.getText());

        // Save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateComment();
            }
        });
    }

    private void updateComment() {
        // Get the updated comment text from the EditText
        String updatedText = editTextComment.getText().toString();

        // Update the selected comment with the new text
        selectedComment.setText(updatedText);

        // TODO: Implement the logic to save the updated comment to the database
        // DatabaseHelper dbHelper = new DatabaseHelper(AllCommentsResultActivity.this);
        // dbHelper.updateComment(selectedComment);

        // Optional: You can set a result to indicate success or failure
        setResult(RESULT_OK);
        finish();
    }
}
