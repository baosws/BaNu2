package com.company.banu.WatchLectures;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.company.banu.R;

import java.util.List;

public class LectureAdapter extends RecyclerView.Adapter<LectureHolder> {
    List<Lecture> lectureList;
    Context mContext;

    public LectureAdapter(Context context, List<Lecture> dataLecture)
    {
        this.mContext = context;
        this.lectureList = dataLecture;
    }

    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_layout, parent, false);
        return new LectureHolder(view);
    }

    @Override
    public void onBindViewHolder(LectureHolder holder, final int position) {
        Lecture lecture = lectureList.get(position);
        holder.bind(lecture);
    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }
}
