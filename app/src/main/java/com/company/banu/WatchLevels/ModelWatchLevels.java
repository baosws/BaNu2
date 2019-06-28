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
                        continue;
                    }
                    for (Object t: topics) {
                        Map<String, Object> topicMap = (Map)t;
                        Topic topic = new Topic(level);
                        level.topics.add(topic);

                        bindDocRefToTopic((DocumentReference)topicMap.get("topic"), topic);

                        ArrayList lectures = (ArrayList) topicMap.get("lectures");
                        if (lectures == null) {
                            continue;
                        }
                        for (int i = 0; i < lectures.size(); ++i) {
                            Map<String, Object> lectureMap = (Map)lectures.get(i);
                            Lecture lecture = new Lecture(topic);
                            lecture.ord = i + 1;
                            topic.lectures.add(lecture);
                            bindDocRefToLecture((DocumentReference)lectureMap.get("lecture"), lecture);
                            lecture.percent = Float.parseFloat(lectureMap.get("percent").toString());
                            lecture.notify("percent");
                        }
                    }
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
                lecture.id = lectureRef.getId();
                lecture.name = (String)documentSnapshot.get("name");
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
            return;
        }
        Backend.storeCache(topicRef.getId(), topic);
        topicRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                topic.id = topicRef.getId();
                topic.name = (String) documentSnapshot.get("name");
                topic.notify("id");
                topic.notify("name");
                Backend.downloadImage(topicRef.getPath() + ".jpg", new CallBack<Bitmap>() {
                    @Override
                    public void call(Bitmap data) {
                        topic.image = data;
                        topic.notify("done");
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
                l.id = levelRef.getId();
                l.name = (String)documentSnapshot.get("name");
                l.notify("name");
                l.notify("id");
                Backend.downloadImage(levelRef.getPath()
                        + ".jpg", new CallBack<Bitmap>() {
                    @Override
                    public void call(Bitmap data) {
                        l.image = data;
                        l.notify("done");
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
