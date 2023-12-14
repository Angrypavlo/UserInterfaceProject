package com.example.petproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.util.SparseBooleanArray;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    private List<Comment> comments = new ArrayList<>();
    private OnEditClickListener onEditClickListener;
    private OnItemClickListener onItemClickListener;
    private Comment selectedComment;

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBoxComment;
        private TextView textViewComment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxComment = itemView.findViewById(R.id.checkBoxComment);
            textViewComment = itemView.findViewById(R.id.textViewComment);
        }

        public void bind(String comment) {
            textViewComment.setText(comment);
        }
    }

    public void setData(List<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnEditClickListener {
        void onEditClick(Comment comment);
    }
    public void removeComment(Comment comment) {
        if (comments != null && comments.contains(comment)) {
            comments.remove(comment);
            notifyDataSetChanged();
        }
    }
    public void setOnEditClickListener(OnEditClickListener listener) {
        this.onEditClickListener = listener;
    }

    public void setSelectedCommentIndex(int position) {
        if (position >= 0 && position < comments.size()) {
            selectedComment = comments.get(position);
            notifyDataSetChanged();
        }
    }

    public Comment getSelectedComment() {
        return selectedComment;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment.getText());

        holder.checkBoxComment.setChecked(selectedComment != null && selectedComment.equals(comment));

        holder.checkBoxComment.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedComment = comment;
            } else if (selectedComment != null && selectedComment.equals(comment)) {
                selectedComment = null;
            }
        });

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
