package com.company.banu.WatchTopics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.company.banu.R;
import com.company.banu.WatchLectures.ListLevelAdapter;

import java.util.ArrayList;
import java.util.List;

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
