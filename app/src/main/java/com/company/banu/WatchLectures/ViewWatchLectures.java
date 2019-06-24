package com.company.banu.WatchLectures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.banu.R;
import com.company.banu.WatchLevels.PresenterWatchLevels;

public class ViewWatchLectures extends AppCompatActivity {
    static int layoutId = R.layout.activity_signin;
    PresenterWatchLevels presenterWatchLevels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        try {
            presenterWatchLevels = new PresenterWatchLevels(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
