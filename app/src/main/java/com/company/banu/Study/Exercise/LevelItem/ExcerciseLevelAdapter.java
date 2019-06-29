package com.company.banu.Study.Exercise.LevelItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.banu.R;

import java.util.List;


public class ExcerciseLevelAdapter extends RecyclerView.Adapter<LevelHolder> {
    List<ExcerciseLevel> excerciseLevelList;


    public ExcerciseLevelAdapter(List<ExcerciseLevel> data)
    {
        excerciseLevelList = data;
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
        ExcerciseLevel excerciseLevel = excerciseLevelList.get(i);
        viewHolder.bind(excerciseLevel);
    }

    @Override
    public int getItemCount() {
        return excerciseLevelList.size();
    }


}
