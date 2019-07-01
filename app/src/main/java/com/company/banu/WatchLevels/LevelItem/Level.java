package com.company.banu.WatchLevels.LevelItem;

import android.graphics.Bitmap;
import android.util.Log;

import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.WatchTopics.TopicItem.Topic;

import java.util.ArrayList;

public class Level extends Notifier<LevelEvent> {
    private String id;
    private String name;
    private Bitmap image;
    private Boolean passed;
    private ArrayList<Topic> topics;
    public Level() {
        super();
        topics = new ArrayList<>();
    }

    public void getPercent(final CallBack<Float> cb) {
        final Float[] res = {0f};
        for (Topic topic: topics) {
            topic.getPercent(new CallBack<Float>() {
                @Override
                public void call(Float data) {
                    res[0] += data;
                }
            });
        }
        cb.call(res[0]);
    }

    public void setId(String id) {
        this.id = id;
        notify(LevelEvent.HadId);
    }

    public void getId(final CallBack<String> cb) {
        addEvent(LevelEvent.HadId, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(id);
            }
        });
    }

    public void setName(String name) {
        this.name = name;
        notify(LevelEvent.HadName);
    }

    public void getName(final CallBack<String> cb) {
        addEvent(LevelEvent.HadName, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(name);
            }
        });
    }

    public void setImage(Bitmap image) {
        this.image = image;
        notify(LevelEvent.HadImage);
    }

    public void getImage(final CallBack<Bitmap> cb) {
        addEvent(LevelEvent.HadImage, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(image);
            }
        });
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
        notify(LevelEvent.NewTopic);
    }

    public void setTopics(ArrayList<Topic> topics) {
        Log.d("btag", String.format("Level:setTopics: name = %s, size = %d", name, topics.size()));
        this.topics = topics;
        notify(LevelEvent.HadTopics);
    }

    public void getTopics(final CallBack<ArrayList<Topic>> cb) {
        Log.d("btag", String.format("Level:getTopics: name = %s, id = %s", name, id));
        addEvent(LevelEvent.HadTopics, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                Log.d("btag", "got topics: size = " + topics.size());
                cb.call(topics);
            }
        });
    }

    public void getAny(CallBack cb) {
        for (LevelEvent event: events.keySet()) {
            addEvent(event, cb);
        }
    }

    public void setPassed(Boolean passed) {
        Log.d("btag", String.format("Level:setTopics: name = %s, size = %d", name, topics.size()));
        this.passed = passed;
        notify(LevelEvent.HadPassed);
    }

    public void getPassed(final CallBack<Boolean> cb) {
        addEvent(LevelEvent.HadPassed, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                Log.d("Nunu", String.format("Level:getPassed: name = %s, passed = %s", name, passed));
                cb.call(passed);
            }
        });
    }
}