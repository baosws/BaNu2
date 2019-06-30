package com.company.banu.WatchLectures.LectureItem;

import android.content.Intent;
import android.telecom.Call;

import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.Quiz.Excercise;
import com.company.banu.WatchLevels.MediaResource;
import com.company.banu.WatchTopics.TopicItem.Topic;

import java.util.ArrayList;

public class Lecture extends Notifier<LectureEvent> {
    private Topic topic;
    private String name;
    private String id;
    Float percent;
    private Integer ord;
    private ArrayList<Excercise> excercises;
    private String description;
    private MediaResource resource;

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

    public void setExcercises(ArrayList<Excercise> excercises) {
        this.excercises = excercises;
        notify(LectureEvent.HadExcercises);
    }

    public void getExcercises(final CallBack<ArrayList<Excercise>> cb) {
        addEvent(LectureEvent.HadExcercises, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(excercises);
            }
        });
    }

    public void setDescription(String description) {
        this.description = description;
        notify(LectureEvent.HadDescription);
    }

    public void getDescription(final CallBack<String> cb) {
        addEvent(LectureEvent.HadDescription, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(description);
            }
        });
    }

    public void setResource(MediaResource resource) {
        this.resource = resource;
        notify(LectureEvent.HadResource);
    }

    public void getResource(final CallBack<MediaResource> cb) {
        addEvent(LectureEvent.HadResource, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(resource);
            }
        });
    }
}
