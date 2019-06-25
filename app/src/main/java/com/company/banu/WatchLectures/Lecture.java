package com.company.banu.WatchLectures;

import com.company.banu.CallBack;

import java.util.ArrayList;

public class Lecture {
    String name;
    String id;
    Float percent;
    Boolean isLock;
    ArrayList<CallBack<Lecture>> observers;

    public Lecture()
    {
        observers = new ArrayList<>();
    }

    public void addObserver(CallBack<Lecture> cb) {
        observers.add(cb);
    }

    public void notifyDone() {
        for (CallBack<Lecture> cb: observers) {
            cb.call(this);
        }
    }
}
