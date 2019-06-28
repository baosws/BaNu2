package com.company.banu.WatchLectures.LectureItem;

import android.util.Log;

import com.company.banu.CallBack;
import com.company.banu.WatchTopics.TopicItem.Topic;

import java.util.ArrayList;
import java.util.HashMap;

public class Lecture {
    public Topic topic;
    public String name;
    public String id;
    public Float percent;
    public Integer ord;
    public Boolean isLock;
    HashMap<String, ArrayList<CallBack<Lecture>>> observers;

    public Lecture(Topic topic)
    {
        this.topic = topic;
        observers = new HashMap<>();
    }

    public Float getPercent() {
        return percent;
    }

    public void addObserver(String event, CallBack<Lecture> cb) {
        if (observers.containsKey(event) == false) {
            observers.put(event, new ArrayList<CallBack<Lecture>>());
        }
        observers.get(event).add(cb);
    }

    public void notify(String event) {
        if (observers.containsKey(event) == false) {
            Log.d("btag", String.format("notifyDone: lecture event not found: %s, %s", id, event));
            return;
        }
        for (CallBack cb: observers.get(event)) {
            cb.call(this);
        }
    }
}
