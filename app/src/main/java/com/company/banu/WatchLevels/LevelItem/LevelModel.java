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
        this.level.addObserver(new CallBack<Level>() {
            @Override
            public void call(Level data) {
                presenter.invalidate();
            }
        });
    }

    public Bitmap getImage() {
        return level.image;
    }

    public String getName() {
        return level.name;
    }
}
