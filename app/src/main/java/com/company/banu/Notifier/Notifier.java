package com.company.banu.Notifier;

import android.util.Log;

import com.company.banu.CallBack;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Notifier<EventType> {
    protected HashMap<EventType, Observer<EventType, ? extends Notifier>> events;

    public Notifier() {
        events = new HashMap<>();
    }

    protected void addEvent(EventType name, CallBack<? extends Notifier> cb) {
        Log.d("btag", "addEvent: " + name.toString());
        if (events.containsKey(name) == false) {
            events.put(name, new Observer<EventType, Notifier>(name, this));
            Log.d("btag", String.format("Notifier:addEvent: this is the first time i met this guy"));
        }
        else {
            Log.d("btag", String.format("%d callbacks avaiable", events.get(name).callBacks.size()));
        }
        events.get(name).addCallback(cb);
    }

    protected void notify(EventType name) {
        Log.d("btag", String.format("notify class %s: event %s", getClass().getCanonicalName(), name.toString()));
        if (events.containsKey(name) == false) {
            events.put(name, new Observer<EventType, Notifier>(name, this));
        }
        else {
            Log.d("btag", "no event names " + name);
        }
        events.get(name).callAll();
    }
}
