package com.company.banu.WatchLevels.LevelItem;

import android.graphics.Bitmap;
import android.util.Log;

import com.company.banu.CallBack;

public class LevelPresenter {
    LevelView view;
    LevelModel levelModel;
    public LevelPresenter(LevelView view, Level level) {
        assert view != null;
        assert level != null;
        this.view = view;
        levelModel = new LevelModel(this, level);
        init();
    }

    private void init() {
        view.getViews();
        levelModel.getName(new CallBack<String>() {
            @Override
            public void call(String data) {
                view.setName(data);
            }
        });

        levelModel.getImage(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                view.setImage(data);
            }
        });

        levelModel.getPassed(new CallBack<Boolean>() {
            @Override
            public void call(Boolean data) {
                Log.d("btag", String.format("Level Presenter: getPassed = " + data));
                view.updatePassed(data);
            }
        });

    }

    public void getLevelId(CallBack<String> cb) {
        levelModel.getId(cb);
    }
}
