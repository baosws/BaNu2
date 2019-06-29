package com.company.banu.WatchLectures;

import com.company.banu.CallBack;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.ArrayList;

public class PresenterWatchLectures {
    WatchLecturesView view;
    ModelWatchLectures modelWatchLectures;
    public PresenterWatchLectures(WatchLecturesView view, Topic topic) {
        this.view = view;
        modelWatchLectures = new ModelWatchLectures(this, topic);
        view.getViews();
        view.initUI();
        init();
    }

    public void init() {
        modelWatchLectures.getLectures(new CallBack<ArrayList<Lecture>>() {
            @Override
            public void call(ArrayList<Lecture> data) {
                view.showLectures(data);
            }
        });
        modelWatchLectures.getTopicName(new CallBack<String>() {
            @Override
            public void call(String data) {
                view.setTitle(data);
            }
        });
    }
}
