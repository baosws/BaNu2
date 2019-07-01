package com.company.banu.WatchLectures.LectureItem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.Study.ActivityStudy;

public class LectureHolder extends RecyclerView.ViewHolder implements LectureView {
    TextView tvLectureID;
    TextView tvLectureName;
    RatingBar rbPercent;
    ImageButton btnPlay;
    LecturePresenter lecturePresenter;

    public LectureHolder(@NonNull View itemView) {
        super(itemView);
        lecturePresenter = new LecturePresenter(this);
        getViews();
    }

    public void getViews() {
        tvLectureID = itemView.findViewById(R.id.tv_lectureID);
        tvLectureName = itemView.findViewById(R.id.tv_lectureName);
        rbPercent = itemView.findViewById(R.id.rb_percent);
        btnPlay = itemView.findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(itemView.getContext(), ActivityStudy.class);
                lecturePresenter.getLectureId(new CallBack<String>() {
                    @Override
                    public void call(String data) {
                        intent.putExtra("lectureId", data);
                        itemView.getContext().startActivity(intent);
                    }
                });
            }
        });
    }

    public void bind(Lecture lecture) {
        lecturePresenter.setLecture(lecture);
        lecturePresenter.rebind();
    }

    public void setLectureId(String id) {
        tvLectureID.setText("Lecture " + id);
    }

    public void setLectureName(String name) {
        tvLectureName.setText(name);
    }

    public void setPercent(float percent) {
        rbPercent.setRating(percent * 3);
    }
}