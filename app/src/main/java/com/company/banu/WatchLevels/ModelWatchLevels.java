package com.company.banu.WatchLevels;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.company.banu.WatchLevels.LevelItem.Level;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;


public class ModelWatchLevels {
    public ModelWatchLevels() {

    }

    public void getAvatar(CallBack<Bitmap> cb) {
        Backend.downloadAvatar(cb);
    }

    public void getLevels(final CallBack<ArrayList<Level>> cb) {
        if (Backend.inCache("levels")) {
            cb.call((ArrayList<Level>)Backend.getCache("levels"));
            return;
        }
        DocumentReference diary = FirebaseFirestore
                .getInstance()
                .collection("diary")
                .document(FirebaseAuth.getInstance().getUid());

        diary.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ArrayList<Level> res = new ArrayList<>();
                ArrayList levels = (ArrayList)documentSnapshot.get("levels");
                if (levels == null) {
                    return;
                }
                for (Object l: levels) {
                    Map<String, Object> levelMap = (Map)l;
                    Level level = new Level();
                    res.add(level);
                    bindDocRefToLevel((DocumentReference) levelMap.get("level"), level);

                    ArrayList topics = (ArrayList) levelMap.get("topics");
                    if (topics == null) {
                        level.getId(new CallBack<String>() {
                            @Override
                            public void call(String data) {
                                Log.d("btag", "topic is null, level " + data);
                            }
                        });
                        continue;
                    }
                    else {
                        level.getId(new CallBack<String>() {
                            @Override
                            public void call(String data) {
                                Log.d("btag", "topic is NOT null, level " + data);
                            }
                        });
                    }
                    final ArrayList<Topic> levelTopics = new ArrayList<>();
                    for (Object t: topics) {
                        Map<String, Object> topicMap = (Map)t;
                        Topic topic = new Topic(level);
                        levelTopics.add(topic);

                        bindDocRefToTopic((DocumentReference)topicMap.get("topic"), topic);

                        ArrayList lectures = (ArrayList) topicMap.get("lectures");
                        if (lectures == null) {
                            topic.getName(new CallBack<String>() {
                                @Override
                                public void call(String data) {
                                    Log.d("btag", "letures is null, topic: " + data);
                                }
                            });
                            continue;
                        }
                        ArrayList<Lecture> topicLectures = new ArrayList<>();
                        for (int i = 0; i < lectures.size(); ++i) {
                            Map<String, Object> lectureMap = (Map)lectures.get(i);
                            Lecture lecture = new Lecture(topic);
                            lecture.setOrd(i + 1);
                            topicLectures.add(lecture);
                            bindDocRefToLecture((DocumentReference)lectureMap.get("lecture"), lecture);
                            lecture.setPercent(Float.parseFloat(lectureMap.get("percent").toString()));
                        }
                        topic.setLectures(topicLectures);
                    }
                    level.getName(new CallBack<String>() {
                        @Override
                        public void call(String data) {
                            Log.d("btag", String.format("ModelWatchLevels:onSuccess: name = %s, size = %d", data, levelTopics.size()));
                        }
                    });
                    level.setTopics(levelTopics);
                }

                Backend.storeCache("levels", res);
                cb.call(res);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "get levels failed ", e);
            }
        });
    }

    private void bindDocRefToLecture(final DocumentReference lectureRef, final Lecture lecture) {
        Backend.storeCache(lectureRef.getId(), lecture);
        lectureRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                lecture.setId(lectureRef.getId());
                lecture.setName((String)documentSnapshot.get("name"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "bindDocRefToLecture onFailure: " + lectureRef.getPath());
            }
        });
    }

    private void bindDocRefToTopic(final DocumentReference topicRef, final Topic topic) {
        if (topicRef == null) {
            Log.d("btag", "topicRef = null");
            return;
        }
        else {
            Log.d("btag:", "bindDocRefToTopic: topicRef != null: " + topicRef.getId());
        }
        Backend.storeCache(topicRef.getId(), topic);
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
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "bindDocRefToTopic onFailure: " + topicRef.getPath());
            }
        });
    }

    private void bindDocRefToLevel(final DocumentReference levelRef, final Level l) {
        Backend.storeCache(levelRef.getId(), l);
        levelRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                l.setId(levelRef.getId());
                l.setName((String)documentSnapshot.get("name"));
                Backend.downloadImage(levelRef.getPath()
                        + ".jpg", new CallBack<Bitmap>() {
                    @Override
                    public void call(Bitmap data) {
                        l.setImage(data);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "bindDocRefToLevel onFailure: " + levelRef.getPath());
            }
        });
    }
}
