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

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.ViewHolder> {

    List<Lecture> lectureList;
    Context mContext;

    public LectureAdapter(Context context, List<Lecture> dataLecture)
    {
        this.mContext = context;
        this.lectureList = dataLecture;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Lecture lecture = lectureList.get(position);
        holder.tvLectureID.setText("Lecture " + lecture.id.toString());
        holder.tvLectureName.setText(lecture.name);
        holder.rbPercent.setRating(lecture.percent);

        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked: " + lectureList.get(position).id.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvLectureID;
        TextView tvLectureName;
        RatingBar rbPercent;
        ImageButton btnPlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLectureID = itemView.findViewById(R.id.tv_lectureID);
            tvLectureName = itemView.findViewById(R.id.tv_lectureName);
            rbPercent = itemView.findViewById(R.id.rb_percent);
            btnPlay = itemView.findViewById(R.id.btn_play);
        }
    }
}
