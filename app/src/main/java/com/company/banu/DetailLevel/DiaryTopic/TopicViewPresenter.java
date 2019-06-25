package com.company.banu.DetailLevel.DiaryTopic;

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
        view.setName(topicModel.getName());
        view.setRating(topicModel.getRating());
        topicModel.getImage(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                view.setImage(data);
            }
        });
    }
}
