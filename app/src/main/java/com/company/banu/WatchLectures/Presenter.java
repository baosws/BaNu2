package com.company.banu.WatchLectures;

import android.app.Activity;

public class Presenter {
    Activity activity;
    Model model;
    public Presenter(Activity activity) {
        this.activity = activity;
        model = new Model();
    }

    public void bindLevelsToView() {

    }
}
