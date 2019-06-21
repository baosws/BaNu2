package com.company.banu.WatchLectures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.banu.R;
import com.company.banu.WatchLevels.Presenter;

public class View extends AppCompatActivity {
    static int layoutId = R.layout.activity_main;
    com.company.banu.WatchLevels.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenter = new Presenter(this);
    }
}
