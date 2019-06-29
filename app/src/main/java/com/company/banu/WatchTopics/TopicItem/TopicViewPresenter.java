package com.company.banu.WatchTopics.TopicItem;

import android.graphics.Bitmap;

import com.company.banu.CallBack;

public class TopicViewPresenter {
    TopicView view;
    TopicModel topicModel;
    TopicViewPresenter(TopicView view, Topic topic) {
        assert view != null;
        assert topic != null;
        this.view = view;
        topicModel = new TopicModel(topic);
        view.getViews();
        init();
    }

    public void init() {
        topicModel.getName(new CallBack<String>() {
            @Override
            public void call(String data) {
                view.setName(data);
            }
        });
        topicModel.getPercent(new CallBack<Float>() {
            @Override
            public void call(Float data) {
                view.setRating(data * 3);
            }
        });
        topicModel.getImage(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                view.setImage(data);
            }
        });
    }

    public void getTopicId(CallBack<String> cb) {
        topicModel.getId(cb);
    }
}
