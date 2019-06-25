package com.company.banu.Study;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.company.banu.R;
import com.company.banu.WatchLevels.PresenterWatchLevels;

public class ViewStudy extends AppCompatActivity {
    static int layoutId = R.layout.activity_study;
    PresenterWatchLevels presenterWatchLevels;
    ViewPager vpStudy;

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
    }

    void initUI()
    {
        vpStudy = findViewById(R.id.vp_study);
        vpStudy.setAdapter(new TabAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vpStudy);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_library_books_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_assignment_black_24dp);
    }
}
