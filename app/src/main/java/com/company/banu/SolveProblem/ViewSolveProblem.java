package com.company.banu.SolveProblem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.company.banu.R;
import com.company.banu.WatchLevels.PresenterWatchLevels;

public class ViewSolveProblem extends AppCompatActivity {
    static int layoutId = R.layout.activity_signin;
    PresenterWatchLevels presenterWatchLevels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenterWatchLevels = new PresenterWatchLevels(this);
    }
}
