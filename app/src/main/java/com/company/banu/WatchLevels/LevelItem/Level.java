package com.company.banu.WatchLevels.LevelItem;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Level extends Notifier<LevelEvent> {
    private String id;
    private String name;
    private Bitmap image;
    private Boolean passed;
    DocumentReference ref;
    private ArrayList<Topic> topics;
    public Level() {
        super();
        topics = new ArrayList<>();
    }

    public DocumentReference getRef() {
        return ref;
    }

    public Level bind(final DocumentReference levelRef) {
        final Level level = this;
        ref = levelRef;
        Backend.putCache(levelRef.getId(), this);
        levelRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                level.setId(levelRef.getId());
                level.setName((String)documentSnapshot.get("name"));
                Backend.downloadImage(levelRef.getPath()
                        + ".jpg", new CallBack<Bitmap>() {
                    @Override
                    public void call(Bitmap data) {
                        level.setImage(data);
                    }
                });
                level.setTopics(getTopics((ArrayList)documentSnapshot.get("topics"), level));
                getDiary(levelRef.getId(), new CallBack<Boolean>() {
                    @Override
                    public void call(Boolean data) {
                        Log.d("Nunu", "bindDocRefToLevel " + levelRef.getId() + " " + data);
                        level.setPassed(data);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "bindDocRefToLevel onFailure: " + levelRef.getPath());
            }
        });
        return this;
    }

    private static ArrayList<Topic> getTopics(ArrayList topicRefs, Level level) {
        if (topicRefs == null) {
            Log.d("btag", String.format("ModelWatchLevels:getTopics: topicRefs is null"));
            return new ArrayList<>();
        }
        final ArrayList<Topic> topics = new ArrayList<>();
        for (Object t: topicRefs) {
            Topic topic = new Topic(level).bind((DocumentReference)t);
            topics.add(topic);
        }
        return topics;
    }

    private static void getDiary(final String id, final CallBack<Boolean> cb) {
        if (Backend.inCache("diary/" + id)) {
            cb.call(((Map<String, Boolean>)Backend.getCache("diary/" + id)).get(id));
            return;
        }
        final DocumentReference docRef = FirebaseFirestore
                .getInstance()
                .document(String.format("diary/%s", FirebaseAuth.getInstance().getUid()));
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot == null) {
                    cb.call(false);
                    return;
                }
                if (documentSnapshot.getData() == null) {
                    cb.call(false);
                    return;
                }
                ArrayList passed = (ArrayList) documentSnapshot.getData().get("passed");
                if (passed == null) {
                    cb.call(false);
                    return;
                }
                Backend.putCache("diary/" + id, passed);
                for (Object o: passed) {
                    if (((DocumentReference)o).getId().equals(id)) {
                        cb.call(true);
                        return;
                    }
                }
                cb.call(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
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