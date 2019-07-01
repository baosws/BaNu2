package com.company.banu.WatchLectures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.company.banu.R;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.company.banu.WatchLectures.LectureItem.LectureAdapter;

import java.util.List;

public class ActivityWatchLectures extends AppCompatActivity implements WatchLecturesView {
    static int layoutId = R.layout.activity_watch_lectures;
    PresenterWatchLectures presenterWatchLectures;
    TextView tbTitle;
    Toolbar toolbar;
    RecyclerView rvLecture;
    ImageView imgPenguin;
    ImageView imgMathBackground;
    ImageButton btnBack;
    ImageButton btnList;
    LectureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        Intent intent = getIntent();
        String topicId = intent.getStringExtra("topicId");
        Topic topic = (Topic)Backend.getCache(topicId);
        presenterWatchLectures = new PresenterWatchLectures(this, topic);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void getViews()
    {
        tbTitle = findViewById(R.id.tb_title);
        rvLecture = findViewById(R.id.rv_lectures);
        toolbar = findViewById(R.id.toolbar);
        imgPenguin = findViewById(R.id.img_penguin);
        imgMathBackground = findViewById(R.id.img_math_pattern);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showLectures(List<Lecture> lectures)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvLecture.setLayoutManager(layoutManager);
        adapter = new LectureAdapter(this, lectures);
        rvLecture.setAdapter(adapter);
        for (Lecture lecture: lectures) {
            lecture.getAny(new CallBack<Lecture>() {
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

    public void setTitle(String title) {
        tbTitle.setText(title);
    }
    public void initUI()
    {
        setSupportActionBar(toolbar);
        Glide.with(this).load(R.mipmap.penguin_study).into(imgPenguin);
        Glide.with(this).load(R.mipmap.math_pattern).into(imgMathBackground);
    }
}