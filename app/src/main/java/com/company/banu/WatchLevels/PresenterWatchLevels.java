package com.company.banu.WatchLevels;

import android.app.Activity;

public class PresenterWatchLevels {
    Activity activity;
    ModelWatchLevels modelWatchLevels;

    public PresenterWatchLevels(Activity activity) throws InterruptedException {
        this.activity = activity;
        modelWatchLevels = new ModelWatchLevels();
        modelWatchLevels.getLevels();
    }
}
