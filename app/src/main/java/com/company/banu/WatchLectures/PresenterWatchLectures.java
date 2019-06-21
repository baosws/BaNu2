package com.company.banu.WatchLectures;

import android.app.Activity;

public class PresenterWatchLectures {
    Activity activity;
    ModelWatchLectures modelWatchLectures;
    public PresenterWatchLectures(Activity activity) {
        this.activity = activity;
        modelWatchLectures = new ModelWatchLectures();
    }

    public void bindLevelsToView() {

    }
}
