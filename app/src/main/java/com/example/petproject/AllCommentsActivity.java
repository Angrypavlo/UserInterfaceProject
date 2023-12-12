package com.example.petproject;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petproject.CommentsAdapter;
import com.example.petproject.Comment;
import com.example.petproject.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

public class AllCommentsActivity extends AppCompatActivity {

    private List<Comment> allComments;
    private CommentsAdapter commentsAdapter;
    private String selectedCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);

        selectedCountry = getIntent().getStringExtra("country");
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

        // Set up the buttons
        Button editButton = findViewById(R.id.buttonEditComment);
        Button deleteButton = findViewById(R.id.buttonDeleteComment);

        // Set click listeners for the buttons
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Determine the selected comment index
                int selectedCommentIndex = getSelectedCommentIndex();
                if (selectedCommentIndex != -1) {
                    showEditDialog(selectedCommentIndex);
                } else {
                    Toast.makeText(AllCommentsActivity.this, "Please select a comment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement delete logic here
                Toast.makeText(AllCommentsActivity.this, "Delete comment logic goes here", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Function to show a dialog for editing comments
    private void showEditDialog(final int selectedCommentIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Comment");

        // Get the selected comment from the list
        final Comment selectedComment = getSelectedComment(selectedCommentIndex);

        // Create an EditText for the user to edit the comment
        final EditText editText = new EditText(this);
        editText.setText(selectedComment.getText());
        builder.setView(editText);

        // Set positive button to save the edited comment
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String editedText = editText.getText().toString();
                if (!editedText.isEmpty()) {
                    // Update the comment in the list
                    selectedComment.setText(editedText);

                    // Update the comment in the database
                    editCommentInDatabase(selectedComment.getId(), editedText);

                    // Refresh the UI
                    refreshUI();
                }
            }
        });

        // Set negative button to cancel the edit
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        // Save the AlertDialog to a variable to access its buttons later
        final AlertDialog editAlertDialog = builder.create();

        // Show the AlertDialog
        editAlertDialog.show();

        // Access the positive button of the AlertDialog
        Button positiveButton = editAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        // Set a click listener for the positive button
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Determine if the comment is valid (not empty)
                String editedText = editText.getText().toString();
                if (!editedText.isEmpty()) {
                    // Edit the comment
                    editComment(selectedCommentIndex, editedText);

                    // Dismiss the dialog
                    editAlertDialog.dismiss();
                } else {
                    // Show an error message if the comment is empty
                    Toast.makeText(AllCommentsActivity.this, "Comment cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Function to edit the comment in the list and database
    private void editComment(int selectedCommentIndex, String editedText) {
        // Update the comment in the list
        Comment editedComment = allComments.get(selectedCommentIndex);
        editedComment.setText(editedText);

        // Update the comment in the database
        editCommentInDatabase(editedComment.getId(), editedText);

        // Refresh the UI
        refreshUI();
    }

    // Function to get the selected comment from the list
    private Comment getSelectedComment(int selectedCommentIndex) {
        return allComments.get(selectedCommentIndex);
    }

    // Function to determine the selected comment index
    private int getSelectedCommentIndex() {
        // You need to implement this based on how you determine the selected comment index.
        // For example, if you have a selectedCommentId, you can iterate through the list to find its index.

        long selectedCommentId = getSelectedCommentId();/* replace with the actual selected comment id */;
        for (int i = 0; i < allComments.size(); i++) {
            if (allComments.get(i).getId() == selectedCommentId) {
                return i;
            }
        }

        return -1; // Return -1 if the comment is not found (handle this case in the onClick)
    }
    private long getSelectedCommentId() {
        return getIntent().getLongExtra("selectedCommentId", -1);
    }
    // Function to update the edited comment in the database
    private void editCommentInDatabase(long commentId, String newCommentText) {
        DatabaseHelper dbHelper = new DatabaseHelper(AllCommentsActivity.this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_COMMENT, newCommentText);

        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(commentId)};

        database.update(DatabaseHelper.TABLE_COMMENTS, values, selection, selectionArgs);

        dbHelper.close();
    }

    // Function to refresh the UI after editing a comment
    private void refreshUI() {
        // Retrieve the updated list of comments from the database
        allComments = getAllComments(selectedCountry);

        // Set data to the adapter
        commentsAdapter.setData(allComments);
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
