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
import com.company.banu.R;
import com.company.banu.WatchLevels.PresenterWatchLevels;

import java.util.ArrayList;
import java.util.List;

public class ViewWatchLectures extends AppCompatActivity {
    static int layoutId = R.layout.activity_watch_lectures;
    PresenterWatchLevels presenterWatchLevels;
    Toolbar toolbar;
    RecyclerView rvLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        try {
            presenterWatchLevels = new PresenterWatchLevels(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initUI();
        getView();
        showLecture();
    }

    void getView()
    {
        rvLecture = findViewById(R.id.rv_lectures);
    }

    void showLecture()
    {
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new Lecture("Count and Write", 1, (float) 1.0));
        lectures.add(new Lecture("Count and Write", 2, (float) 1.0));
        lectures.add(new Lecture("Count and Write", 3, (float) 1.0));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvLecture.setLayoutManager(layoutManager);
        LectureAdapter adapter = new LectureAdapter(this, lectures);
        rvLecture.setAdapter(adapter);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvLecture);
    }

    void initUI()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imgPenguin = findViewById(R.id.img_penguin);
        ImageView imgMathBackground = findViewById(R.id.img_math_pattern);
        Glide.with(this).load(R.mipmap.penguin_study).into(imgPenguin);
        Glide.with(this).load(R.mipmap.math_pattern).into(imgMathBackground);
    }
}
