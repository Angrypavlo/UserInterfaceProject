package com.example.petproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import android.database.Cursor;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.sqlite.SQLiteDatabase;

import com.example.petproject.Comment;
import com.example.petproject.CommentsAdapter;
import com.example.petproject.DatabaseHelper;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

public class AllCommentsActivity extends AppCompatActivity implements CommentsAdapter.OnItemClickListener {

    private List<Comment> allComments;
    private CommentsAdapter commentsAdapter;
    private static final int EDIT_COMMENT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);

        String selectedCountry = getIntent().getStringExtra("country");
        if (selectedCountry != null) {
            getSupportActionBar().setTitle(selectedCountry);
        }

        allComments = getIntent().getParcelableArrayListExtra("allComments");
        commentsAdapter = new CommentsAdapter();

        RecyclerView recyclerViewComments = findViewById(R.id.recyclerViewComments);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewComments.setAdapter(commentsAdapter);

        commentsAdapter.setData(allComments);
        commentsAdapter.setOnItemClickListener(this);

        Button editButton = findViewById(R.id.buttonEditComment);
        editButton.setOnClickListener(view -> {
            Comment selectedComment = commentsAdapter.getSelectedComment();
            if (selectedComment != null) {
                startEditActivity(selectedComment);
            } else {
                Toast.makeText(this, "No comment selected", Toast.LENGTH_SHORT).show();
            }
        });

        Button deleteButton = findViewById(R.id.buttonDeleteComment);
        deleteButton.setOnClickListener(view -> {
            Comment selectedComment = commentsAdapter.getSelectedComment();
            if (selectedComment != null) {
                deleteComment(selectedComment);
                showAllComments(selectedCountry);
            } else {
                Toast.makeText(this, "No comment selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteComment(Comment comment) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            String selection = DatabaseHelper.COLUMN_ID + " = ?";
            String[] selectionArgs = {String.valueOf(comment.getId())};

            int deletedRows = database.delete(
                    DatabaseHelper.TABLE_COMMENTS,
                    selection,
                    selectionArgs
            );
            Log.d("DeleteComment", "Deleted Rows: " + deletedRows);

            dbHelper.close();

            if (deletedRows > 0) {
                commentsAdapter.removeComment(comment);
                refreshUI();
                Toast.makeText(this, "Comment deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to delete comment", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("DeleteComment", "Error deleting comment: " + e.getMessage());
            Toast.makeText(this, "Error deleting comment", Toast.LENGTH_SHORT).show();
        }finally {
            dbHelper.close();
        }
    }

    private void showAllComments(String selectedCountry) {
        List<Comment> updatedComments = getAllComments(selectedCountry);
        commentsAdapter.setData(updatedComments);
        commentsAdapter.notifyDataSetChanged();
        allComments = updatedComments;
        Toast.makeText(this, "Comment deleted", Toast.LENGTH_SHORT).show();
    }

    private void refreshUI() {
        allComments = getAllCommentsFromDatabase();
        commentsAdapter.setData(allComments);
        commentsAdapter.notifyDataSetChanged();
    }

    private List<Comment> getAllCommentsFromDatabase() {
        String selectedCountry = getIntent().getStringExtra("country");
        return getAllComments(selectedCountry);
    }

    private List<Comment> getAllComments(String country) {
        List<Comment> comments = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] projection = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_COMMENT
                // Add other columns as needed
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
            int columnIndexId = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            int columnIndexComment = cursor.getColumnIndex(DatabaseHelper.COLUMN_COMMENT);

            do {
                long id = cursor.getLong(columnIndexId);
                String commentText = cursor.getString(columnIndexComment);

                Comment comment = new Comment(commentText);
                comment.setId(id);
                comments.add(comment);
            } while (cursor.moveToNext());

            cursor.close();
        }

        dbHelper.close();
        return comments;
    }

    @Override
    public void onItemClick(int position) {
        commentsAdapter.setSelectedCommentIndex(position);
        Comment selectedComment = allComments.get(position);
        showEditDialog(selectedComment);
    }

    private void showEditDialog(final Comment selectedComment) {
        Intent intent = new Intent(this, AllCommentsResultActivity.class);
        intent.putExtra("selectedComment", selectedComment);
        startActivityForResult(intent, EDIT_COMMENT_REQUEST);
    }

    private void startEditActivity(Comment selectedComment) {
        Intent intent = new Intent(this, AllCommentsResultActivity.class);
        intent.putExtra("selectedComment", selectedComment);
        startActivityForResult(intent, EDIT_COMMENT_REQUEST);
    }
}
