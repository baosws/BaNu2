package com.company.banu.WatchLevels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.banu.R;

public class ViewWatchLevels extends AppCompatActivity {
    static int layoutId = R.layout.activity_watch_levels;
    PresenterWatchLevels presenterWatchLevels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenterWatchLevels = new PresenterWatchLevels(this);
    }
}
