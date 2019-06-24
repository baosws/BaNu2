package com.company.banu.WatchLevels;

import android.app.Activity;
import android.util.Log;

import com.company.banu.CallBack;

import java.util.ArrayList;

public class PresenterWatchLevels {
    Activity activity;
    ModelWatchLevels modelWatchLevels;

    public PresenterWatchLevels(Activity activity) throws InterruptedException {
        this.activity = activity;
        modelWatchLevels = new ModelWatchLevels();
        modelWatchLevels.getLevels(new CallBack<ArrayList<Level>>() {
            @Override
            public void call(ArrayList<Level> data) {
                for (Level level: data) {
                    Log.d("btag", level.name + "; " + level.level);
                }
            }
        });
    }
}
