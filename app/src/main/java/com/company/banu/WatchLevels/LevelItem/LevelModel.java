package com.company.banu.WatchLevels.LevelItem;

import android.graphics.Bitmap;

import com.company.banu.Backend;
import com.company.banu.CallBack;


public class LevelModel {
    Level level;
    LevelPresenter presenter;
    LevelModel(final LevelPresenter presenter, Level level) {
        assert level != null;
        assert presenter != null;
        this.presenter = presenter;
        this.level = level;
    }

    public void getImage(CallBack<Bitmap> cb) {
        level.getImage(cb);
    }

    public void getName(CallBack<String> cb) {
        level.getName(cb);
    }

    public void getId(CallBack<String> cb) {
        level.getId(cb);
    }
}
