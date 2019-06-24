package com.company.banu.WatchTopics;

import android.app.Activity;

public class PresenterWatchTopics {
    Activity activity;
    ModelWatchTopics modelWatchTopics;
    public PresenterWatchTopics(Activity activity) {
        this.activity = activity;
        modelWatchTopics = new ModelWatchTopics();
    }
}
