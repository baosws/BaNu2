package com.company.banu.WatchLectures;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.WatchLevels.PresenterWatchLevels;
import com.company.banu.WatchLevels.WatchLevelView;

import java.util.ArrayList;
import java.util.List;

public class ActivityWatchLectures extends AppCompatActivity implements WatchLecturesView {
    static int layoutId = R.layout.activity_watch_lectures;
    PresenterWatchLectures presenterWatchLectures;
    Toolbar toolbar;
    RecyclerView rvLecture;
    ImageView imgPenguin;
    ImageView imgMathBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenterWatchLectures = new PresenterWatchLectures(this);
    }

    public void getViews()
    {
        rvLecture = findViewById(R.id.rv_lectures);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgPenguin = findViewById(R.id.img_penguin);
        imgMathBackground = findViewById(R.id.img_math_pattern);
    }

    public void showLectures(List<Lecture> lectures)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvLecture.setLayoutManager(layoutManager);
        final LectureAdapter adapter = new LectureAdapter(this, lectures);
        rvLecture.setAdapter(adapter);
        for (Lecture lecture: lectures) {
            lecture.addObserver(new CallBack<Lecture>() {
                @Override
                public void call(Lecture data) {
                    adapter.notifyDataSetChanged();
                    rvLecture.invalidate();
                }
            });
        }
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvLecture);
    }

    public void initUI()
    {
        setSupportActionBar(toolbar);
        Glide.with(this).load(R.mipmap.penguin_study).into(imgPenguin);
        Glide.with(this).load(R.mipmap.math_pattern).into(imgMathBackground);
    }
}
