package com.company.banu.WatchTopics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.company.banu.R;

public class ViewWatchTopics extends AppCompatActivity {
    static int layoutId = R.layout.activity_watch_levels;
    PresenterWatchTopics presenterWatchLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenterWatchLevels = new PresenterWatchTopics(this);
    }
}
