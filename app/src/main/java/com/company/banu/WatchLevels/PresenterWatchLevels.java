package com.company.banu.WatchLevels;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.company.banu.CallBack;
import com.company.banu.WatchLevels.LevelItem.Level;

import java.util.ArrayList;

public class PresenterWatchLevels {
    WatchLevelView view;
    ModelWatchLevels modelWatchLevels;

    public PresenterWatchLevels(WatchLevelView view) {
        this.view = view;
        modelWatchLevels = new ModelWatchLevels();
        view.getViews();
        init();
    }

    private void init() {
        modelWatchLevels.getLevels(new CallBack<ArrayList<Level>>() {
            @Override
            public void call(ArrayList<Level> data) {
                view.loadGridviewListLevel(data);
            }
        });
        modelWatchLevels.getAvatar(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                view.setAvatar(data);
            }
        });
    }
}
