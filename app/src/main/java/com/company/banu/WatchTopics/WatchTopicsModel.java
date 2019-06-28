package com.company.banu.WatchTopics;

import android.graphics.Bitmap;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.company.banu.WatchLevels.LevelItem.Level;

import java.util.ArrayList;

public class WatchTopicsModel {
    Level level;
    WatchTopicsModel(Level level) {
        this.level = level;
    }

    void getAvatar(CallBack<Bitmap> cb) {
        Backend.downloadAvatar(cb);
    }

    void getTopics(final CallBack<ArrayList<Topic>> cb) {
        cb.call(level.topics);
    }

    public Float getPercent() {
        return level.getPercent();
    }
}
