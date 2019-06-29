package com.company.banu.WatchTopics;

import android.graphics.Bitmap;
import android.util.Log;

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
                Log.d("btag", "call: loadGridViewListTopics:setTopics");
                view.loadGridViewListTopics(data);
            }
        });
        watchTopicsModel.getAvatar(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                Log.d("btag", "call: loadGridViewListTopics:getAvatar");
                view.loadAvatar(data);
            }
        });
        watchTopicsModel.getPercent(new CallBack<Float>() {
            @Override
            public void call(Float data) {
                Log.d("btag", "call: loadGridViewListTopics:setPercent" + data);
                view.setPercent(data);
            }
        });
        watchTopicsModel.getName(new CallBack<String>() {
            @Override
            public void call(String data) {
                Log.d("btag", "call: loadGridViewListTopics:setLevelName");
                view.setLevelName(data);
            }
        });
    }
}
