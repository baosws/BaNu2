package com.company.banu.WatchTopics;

import android.graphics.Bitmap;

import com.company.banu.CallBack;
import com.company.banu.WatchLevels.LevelItem.Level;
import com.company.banu.WatchTopics.TopicItem.Topic;

import java.util.ArrayList;

public class WatchTopicsPresenter {
    private final WatchTopicsView view;
    WatchTopicsModel watchTopicsModel;

    WatchTopicsPresenter(WatchTopicsView view, Level level) {
        this.view = view;
        watchTopicsModel = new WatchTopicsModel(level);
        view.getViews();
        init();
    }

    private void init() {
        watchTopicsModel.getTopics(new CallBack<ArrayList<Topic>>() {
            @Override
            public void call(ArrayList<Topic> data) {
                view.loadGridViewListTopics(data);
            }
        });

        watchTopicsModel.getAvatar(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                view.loadAvatar(data);
            }
        });
    }
}
