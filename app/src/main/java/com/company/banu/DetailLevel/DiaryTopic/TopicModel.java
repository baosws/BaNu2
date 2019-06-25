package com.company.banu.DetailLevel.DiaryTopic;

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

    public String getName() {
        Log.d("btag", String.format("getName: %s", topic.name));
        return topic.name;
    }

    public void getImage(CallBack<Bitmap> cb) {
        Backend.downloadImage("topics/"
        + topic.id
        + ".jpg", cb);
    }

    public float getRating() {
        return topic.percent;
    }
}
