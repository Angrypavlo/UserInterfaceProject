package com.example.petproject;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import com.example.petproject.CommentsAdapter;
import com.example.petproject.Comment;
import com.example.petproject.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class AllCommentsActivity extends AppCompatActivity implements CommentsAdapter.OnItemClickListener {

    private List<Comment> allComments;
    private CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);

        String selectedCountry = getIntent().getStringExtra("country");
        if (selectedCountry != null) {
            getSupportActionBar().setTitle(selectedCountry);
        }

        // Retrieve the list of comments from the intent
        allComments = getIntent().getParcelableArrayListExtra("allComments");

        // Set up the RecyclerView
        RecyclerView recyclerViewComments = findViewById(R.id.recyclerViewComments);
        commentsAdapter = new CommentsAdapter();
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewComments.setAdapter(commentsAdapter);

        // Set data to the adapter
        commentsAdapter.setData(allComments);

        // Set the click listener for the adapter
        commentsAdapter.setOnItemClickListener(this);

        // Set up the buttons
        Button editButton = findViewById(R.id.buttonEditComment);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Determine the selected comment index
                int selectedCommentIndex = commentsAdapter.getSelectedCommentIndex();

                if (selectedCommentIndex != -1) {
                    // Get the selected comment
                    Comment selectedComment = getSelectedComment(selectedCommentIndex);

                    // Create an Intent to launch the Edit Comments Activity
                    Intent intent = new Intent(AllCommentsActivity.this, AllCommentsResultActivity.class);

                    // Pass the selected comment to the Edit Comments Activity
                    intent.putExtra("selectedComment", selectedComment);

                    // Start the Edit Comments Activity
                    startActivity(intent);
                } else {
                    // No comment is selected, you can choose to do nothing or show a different message
                    // For example, you might want to show a Toast message indicating that no comment is selected
                    Toast.makeText(AllCommentsActivity.this, "No comment selected", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Set up the buttons
        Button deleteButton = findViewById(R.id.buttonDeleteComment);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement delete logic here
                Toast.makeText(AllCommentsActivity.this, "Delete comment logic goes here", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Add this method to get the selected comment from the list
    private Comment getSelectedComment(int selectedCommentIndex) {
        if (selectedCommentIndex >= 0 && selectedCommentIndex < allComments.size()) {
            return allComments.get(selectedCommentIndex);
        } else {
            return null; // Handle the case where the index is out of bounds
        }
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click here, e.g., show edit dialog
        Comment selectedComment = allComments.get(position);
        showEditDialog(selectedComment);
    }

    private void showEditDialog(final Comment selectedComment) {
        Log.d("AllCommentsActivity", "showEditDialog: Oluşturulan Intent: " + selectedComment.getText());

        Intent intent = new Intent(AllCommentsActivity.this, AllCommentsResultActivity.class);
        intent.putExtra("selectedComment", selectedComment);
        startActivity(intent);
    }

    // Function to refresh the UI after editing a comment
    private void refreshUI() {
        // Retrieve the updated list of comments from the database
        allComments = getAllCommentsFromDatabase();

        // Set data to the adapter
        commentsAdapter.setData(allComments);
    }

    // Function to retrieve all comments from the database
    private List<Comment> getAllCommentsFromDatabase() {
        String selectedCountry = getIntent().getStringExtra("country");
        return getAllComments(selectedCountry);
    }

    // Function to retrieve all comments for a specific country from the database
    private List<Comment> getAllComments(String country) {
        List<Comment> comments = new ArrayList<>();

        // Replace the following logic with your actual code to fetch comments from the database
        DatabaseHelper dbHelper = new DatabaseHelper(AllCommentsActivity.this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_COMMENT
                // Add other columns if needed
        };

        String selection = DatabaseHelper.COLUMN_COUNTRY + " = ?";
        String[] selectionArgs = {country};

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_COMMENTS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long commentId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String commentText = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_COMMENT));

                Comment comment = new Comment(commentText);
                comment.setId(commentId);

                comments.add(comment);
            } while (cursor.moveToNext());

            cursor.close();
        }

        dbHelper.close();

        return comments;
    }
}