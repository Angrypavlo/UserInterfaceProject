package com.example.petproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    private List<Comment> comments = new ArrayList<>();

    public void setData(List<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment.getText());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewComment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewComment = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String comment) {
            textViewComment.setText(comment);
        }
    }
}
