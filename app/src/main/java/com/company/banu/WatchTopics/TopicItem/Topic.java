package com.company.banu.WatchTopics.TopicItem;

import android.graphics.Bitmap;
import android.util.Log;

import com.company.banu.CallBack;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.company.banu.WatchLevels.LevelItem.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Topic {
    Level level;
    public String id;
    public String name;
    public Bitmap image;
    public ArrayList<Lecture> lectures;
    HashMap<String, ArrayList<CallBack<Topic>>> observers;
    Semaphore semaphore;
    public Topic(Level level) {
        this.level = level;
        this.lectures = new ArrayList<>();
        observers = new HashMap<>();
        semaphore = new Semaphore(1);
    }

    public Float getPercent() {
        Float res = 0f;
        for (Lecture lecture: lectures) {
            res += lecture.getPercent();
        }
        return res;
    }

    public void addObserver(String event, CallBack<Topic> cb) {
        if (observers.containsKey(event) == false) {
            observers.put(event, new ArrayList<CallBack<Topic>>());
        }
        observers.get(event).add(cb);
    }

    public void notify(String event) {
        if (observers.containsKey(event) == false) {
            Log.d("btag", String.format("notifyDone: topic event not found: %s, %s", id, event));
            return;
        }
        for (CallBack cb: observers.get(event)) {
            cb.call(this);
        }
    }
}