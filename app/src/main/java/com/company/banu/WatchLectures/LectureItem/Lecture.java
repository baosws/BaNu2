package com.company.banu.WatchLectures.LectureItem;

import android.content.Intent;
import android.telecom.Call;

import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.WatchTopics.TopicItem.Topic;

public class Lecture extends Notifier<LectureEvent> {
    private Topic topic;
    private String name;
    private String id;
    Float percent;
    private Integer ord;

    public Lecture(Topic topic)
    {
        this.topic = topic;
    }

    public void setName(String name) {
        this.name = name;
        notify(LectureEvent.HadName);
    }

    public void getName(final CallBack<String> cb) {
        addEvent(LectureEvent.HadName, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(name);
            }
        });
    }

    public void setId(String id) {
        this.id = id;
        notify(LectureEvent.HadId);
    }

    public void getId(final CallBack<String> cb) {
        addEvent(LectureEvent.HadId, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(id);
            }
        });
    }

    public void setPercent(Float percent) {
        this.percent = percent;
        notify(LectureEvent.HadPercent);
    }

    public void getPercent(final CallBack<Float> cb) {
        addEvent(LectureEvent.HadPercent, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(percent);
            }
        });
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
        notify(LectureEvent.HadOrd);
    }

    public void getOrd(final CallBack<Integer> cb) {
        addEvent(LectureEvent.HadOrd, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(ord);
            }
        });
    }

    public void getAny(CallBack<Lecture> cb) {
        for (LectureEvent event: events.keySet()) {
            addEvent(event, cb);
        }
    }
}
