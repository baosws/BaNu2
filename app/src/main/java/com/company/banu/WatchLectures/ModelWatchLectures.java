package com.company.banu.WatchLectures;

import com.company.banu.CallBack;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.ArrayList;

public class ModelWatchLectures {
    PresenterWatchLectures presenter;
    Topic topic;
    ModelWatchLectures(PresenterWatchLectures presenterWatchLectures, Topic topic) {
        this.presenter = presenterWatchLectures;
        this.topic = topic;
    }

    public void getLectures(CallBack<ArrayList<Lecture>> cb) {
        topic.getLectures(cb);
    }

    public void getTopicName(CallBack<String> cb) {
        topic.getName(cb);
    }
}
