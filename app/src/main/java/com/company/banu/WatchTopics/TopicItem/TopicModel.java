package com.company.banu.WatchTopics.TopicItem;

import android.graphics.Bitmap;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;

class TopicModel {
    Topic topic;
    TopicModel(Topic topic) {
        this.topic = topic;
        assert topic != null;
    }

    public void getName(CallBack<String> cb) {
        topic.getName(cb);
    }

    public void getImage(final CallBack<Bitmap> cb) {
        topic.getImage(cb);
    }

    public void getId(CallBack<String> cb) {
        topic.getId(cb);
    }

    public void getPercent(CallBack<Float> cb) {
        topic.getPercent(cb);
    }
}