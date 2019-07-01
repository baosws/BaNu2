package com.company.banu.WatchTopics.TopicItem;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.company.banu.WatchLevels.LevelItem.Level;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Topic extends Notifier<TopicEvent> {
    private Level level;
    private String id;
    private String name;
    private Bitmap image;
    private ArrayList<Lecture> lectures;
    DocumentReference ref;
    public Topic(Level level) {
        this.level = level;
        this.lectures = new ArrayList<>();
    }

    public void getPercent(final CallBack<Float> cb) {
        final Float[] res = {0f};
        for (Lecture lecture: lectures) {
            lecture.getPercent(new CallBack<Float>() {
                @Override
                public void call(Float data) {
                    res[0] += data;
                }
            });
        }
        cb.call(res[0]);
    }

    public void setName(String name) {
        this.name = name;
        notify(TopicEvent.HadName);
    }

    public void getName(final CallBack<String> cb) {
        addEvent(TopicEvent.HadName, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(name);
            }
        });
    }

    public void setImage(Bitmap image) {
        this.image = image;
        notify(TopicEvent.HadImage);
    }

    public void getImage(final CallBack<Bitmap> cb) {
        addEvent(TopicEvent.HadImage, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(image);
            }
        });
    }

    public void setId(String id) {
        this.id = id;
        notify(TopicEvent.HadId);
    }

    public void getId(final CallBack<String> cb) {
         addEvent(TopicEvent.HadId, new CallBack<Notifier>() {
             @Override
             public void call(Notifier data) {
                 cb.call(id);
             }
         });
    }

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
        notify(TopicEvent.NewLecture);
    }
    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
        notify(TopicEvent.HadLectures);
    }

    public void getLectures(final CallBack<ArrayList<Lecture>> cb) {
        addEvent(TopicEvent.HadLectures, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(lectures);
            }
        });
    }

    public void getAny(CallBack cb) {
        for (TopicEvent event: events.keySet()) {
            addEvent(event, cb);
        }
    }

    public Topic bind(final DocumentReference topicRef) {
        this.ref = topicRef;
        final Topic topic = this;
        if (topicRef == null) {
            Log.d("btag", "topicRef = null");
            return null;
        }
        else {
            Log.d("btag:", "bindDocRefToTopic: topicRef != null: " + topicRef.getId());
        }
        Backend.putCache(topicRef.getId(), topic);
        topicRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                topic.setId(topicRef.getId());
                topic.setName((String)documentSnapshot.get("name"));
                Backend.downloadImage(topicRef.getPath() + ".jpg", new CallBack<Bitmap>() {
                    @Override
                    public void call(Bitmap data) {
                        topic.setImage(data);
                    }
                });
                topic.setLectures(getLectures((ArrayList)documentSnapshot.get("lectures"), topic));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "bindDocRefToTopic onFailure: " + topicRef.getPath());
            }
        });
        return this;
    }

    private static ArrayList<Lecture> getLectures(ArrayList lectures, Topic topic) {
        if (lectures == null) {
            Log.d("btag", String.format("ModelWatchLevels:getLectures: lectures is null"));
            return new ArrayList<>();
        }
        ArrayList<Lecture> topicLectures = new ArrayList<>();
        for (int i = 0; i < lectures.size(); ++i) {
            Lecture lecture = new Lecture(topic).bind((DocumentReference)lectures.get(i));;
            lecture.setOrd(i + 1);
            topicLectures.add(lecture);
        }
        return topicLectures;
    }
}