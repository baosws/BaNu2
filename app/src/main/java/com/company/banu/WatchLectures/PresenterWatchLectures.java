package com.company.banu.WatchLectures;

import android.app.Activity;

import com.company.banu.CallBack;

import java.util.ArrayList;

public class PresenterWatchLectures {
    WatchLecturesView view;
    ModelWatchLectures modelWatchLectures;
    public PresenterWatchLectures(WatchLecturesView view) {
        this.view = view;
        modelWatchLectures = new ModelWatchLectures(this);
        view.getViews();
        view.initUI();
        init();
    }

    public void init() {
        modelWatchLectures.getLectures(new CallBack<ArrayList<Lecture>>() {
            @Override
            public void call(ArrayList<Lecture> data) {
                view.showLectures(data);
            }
        });
    }
}
