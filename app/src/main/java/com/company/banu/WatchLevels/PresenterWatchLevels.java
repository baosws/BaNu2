package com.company.banu.WatchLevels;

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
            public void call(final ArrayList<Level> data) {
                view.loadGridviewListLevel(data);
                Log.d("btag", String.format("PresenterWatchLevels:call: size = %d", data.size()));
                data.get(0).getPassed(new CallBack<Boolean>() {
                    @Override
                    public void call(Boolean passed) {
                        Log.d("btag", String.format("PresenterWatchLevels:call: nooooooooooooo " + passed));
                        if (passed == false) {
                            modelWatchLevels.enableNewLevel(data.get(0));
                        }
                    }
                });
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
