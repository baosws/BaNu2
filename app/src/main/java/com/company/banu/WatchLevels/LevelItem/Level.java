package com.company.banu.WatchLevels.LevelItem;

import android.graphics.Bitmap;
import android.util.Log;

import com.company.banu.CallBack;
import com.company.banu.WatchTopics.TopicItem.Topic;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    public String id;
    public String name;
    public Float percent;
    public Bitmap image;
    public ArrayList<Topic> topics;

    HashMap<String, ArrayList<CallBack<Level>>> observers;
    public Level() {
        observers = new HashMap<>();
        topics = new ArrayList<>();
    }

    public void addObserver(String event, CallBack<Level> cb) {
        if (observers.containsKey(event) == false) {
            observers.put(event, new ArrayList<CallBack<Level>>());
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