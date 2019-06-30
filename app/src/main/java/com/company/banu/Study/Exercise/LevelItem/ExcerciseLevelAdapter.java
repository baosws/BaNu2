package com.company.banu.Study.Exercise.LevelItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.banu.R;
import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.List;


public class ExcerciseLevelAdapter extends RecyclerView.Adapter<ExcerciseLevelHolder> {
    List<ExcerciseLevel> excerciseLevelList;

    public ExcerciseLevelAdapter(List<ExcerciseLevel> data) {
        excerciseLevelList = data;
    }

    @NonNull
    @Override
    public ExcerciseLevelHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.level_exercise_item_layout, parent, false);
        return new ExcerciseLevelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExcerciseLevelHolder viewHolder, int i) {
        ExcerciseLevel excerciseLevel = excerciseLevelList.get(i);
        viewHolder.bind(excerciseLevel);
    }

    @Override
    public int getItemCount() {
        return excerciseLevelList.size();
    }
}
