package com.company.banu.WatchLevels.LevelItem;

import android.graphics.Bitmap;
import android.telecom.Call;

import com.company.banu.CallBack;

import java.util.ArrayList;

public class Level {
    public String id;
    public String name;
    public int level;
    public float percent;
    public Bitmap image;
    ArrayList<CallBack<Level>> observers;
    public Level() {
        observers = new ArrayList<>();
    }
    public void addObserver(CallBack<Level> cb) {
        observers.add(cb);
    }

    public void notifyDone() {
        for (CallBack<Level> cb: observers) {
            cb.call(this);
        }
    }
}