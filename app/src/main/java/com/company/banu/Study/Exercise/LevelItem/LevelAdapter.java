package com.company.banu.Study.Exercise.LevelItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.banu.R;

import java.util.List;


public class LevelAdapter extends RecyclerView.Adapter<LevelHolder> {
    List<Level> levelList;


    public LevelAdapter(List<Level> data)
    {
        levelList = data;
    }

    @NonNull
    @Override
    public LevelHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.level_exercise_item_layout, parent, false);
        return new LevelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelHolder viewHolder, int i) {
        Level level = levelList.get(i);
        viewHolder.bind(level);
    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }


}
