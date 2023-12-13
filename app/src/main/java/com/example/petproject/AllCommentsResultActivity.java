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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import com.example.petproject.CommentsAdapter;
import com.example.petproject.Comment;
import com.example.petproject.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;public class AllCommentsResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_comments_dialog);

        // Retrieve the selected comment from the intent
        Comment selectedComment = getIntent().getParcelableExtra("selectedComment");

        // Use the selectedComment as needed in your layout
    }
}
