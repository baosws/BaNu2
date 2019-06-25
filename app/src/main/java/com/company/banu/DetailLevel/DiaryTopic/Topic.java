package com.company.banu.DetailLevel.DiaryTopic;

import android.graphics.Bitmap;

import com.company.banu.CallBack;

import java.util.ArrayList;

public class Topic {
    public String id;
    public String name;
    public float percent;
    public Bitmap image;
    ArrayList<CallBack<Topic>> observers;
    public Topic() {
        observers = new ArrayList<>();
    }
    public void addObserver(CallBack<Topic> cb) {
        observers.add(cb);
    }
    public void notifyDone() {
        for (CallBack cb: observers) {
            cb.call(this);
        }
    }
}