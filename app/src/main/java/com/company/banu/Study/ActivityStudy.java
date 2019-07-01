package com.company.banu.Study;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.company.banu.Backend;
import com.company.banu.R;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.company.banu.WatchLevels.PresenterWatchLevels;

public class ActivityStudy extends AppCompatActivity implements StudyView {
    static int layoutId = R.layout.activity_study;
    PresenterStudy presenterStudy;
    TextView tb_title;
    ViewPager vpStudy;
    ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        Intent intent = getIntent();
        String lectureId = intent.getStringExtra("lectureId");
        Lecture lecture = (Lecture)Backend.getCache(lectureId);
        presenterStudy = new PresenterStudy(this, lecture);
    }

    @Override
    public void initUI(Lecture lecture)
    {
        tb_title = findViewById(R.id.tb_title);
        vpStudy = findViewById(R.id.vp_study);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (presenterStudy == null) {
            Log.d("btag", "initUI: presenter is null");
        }
        vpStudy.setAdapter(new TabAdapter(getSupportFragmentManager(), lecture));
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vpStudy);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_library_books_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_assignment_black_24dp);
    }

    @Override
    public void setTitle(String title) {
        tb_title.setText(title);
    }
}
