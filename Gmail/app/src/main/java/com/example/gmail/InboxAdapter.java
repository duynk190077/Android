package com.example.gmail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {

    List<InboxModel> items;

    public InboxAdapter(List<InboxModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public InboxAdapter.InboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbox_layout, parent, false);
        return new InboxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxAdapter.InboxViewHolder holder, int position) {
        String name = items.get(position).getName();
        String firstChar = "" + name.charAt(0);
        holder.nameView.setText(name);
        holder.avatarView.setText(firstChar);
        holder.subjectView.setText(items.get(position).getSubject());
        holder.descriptionView.setText(items.get(position).getDescription());
        holder.timeView.setText(items.get(position).getTime());
        holder.avatarView.setBackgroundTintList(ColorStateList.valueOf(items.get(position).getAvatarColor()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class InboxViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, subjectView, descriptionView, timeView;
        Button avatarView;
        RatingBar favoriteView;
        public InboxViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.text_name);
            subjectView = itemView.findViewById(R.id.text_subject);
            descriptionView = itemView.findViewById(R.id.text_description);
            timeView = itemView.findViewById(R.id.text_time);
            avatarView = itemView.findViewById(R.id.button_avatar);
            favoriteView = itemView.findViewById(R.id.ratingBar);
        }
    }
}
