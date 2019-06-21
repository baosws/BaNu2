package com.company.banu.WatchLevels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.banu.R;

public class View extends AppCompatActivity {
    static int layoutId = R.layout.activity_main;
    Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenter = new Presenter(this);
    }
}
