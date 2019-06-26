package com.company.banu.Study.Exercise;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.R;

import java.util.List;


public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {
    List<Level> levelList;


    public LevelAdapter(List<Level> data)
    {
        levelList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.level_exercise_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelAdapter.ViewHolder viewHolder, int i) {
        Level level = levelList.get(i);
        viewHolder.tvName.setText(level.name);
        viewHolder.tvName.setBackgroundColor(level.color);
    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvScore;
        ImageView imgState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_levelName);
            tvScore = itemView.findViewById(R.id.tv_scoreExercise);
            imgState = itemView.findViewById(R.id.img_state);
        }
    }
}
