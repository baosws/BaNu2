package com.company.banu.WatchLevels;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.telecom.Call;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.Quiz.Excercise;
import com.company.banu.Quiz.QuizLevel;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.company.banu.WatchLevels.LevelItem.Level;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
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
        CollectionReference levelsRef = FirebaseFirestore
                .getInstance()
                .collection("levels");

        levelsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                ArrayList<Level> levels = new ArrayList<>();
                for (DocumentSnapshot doc: queryDocumentSnapshots.getDocuments()) {
                    final Level level = new Level();
                    bindDocRefToLevel(doc.getReference(), level);
                    levels.add(level);
                }
                Backend.putCache("levels", levels);
                cb.call(levels);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private ArrayList<Topic> getTopics(ArrayList topicRefs, Level level) {
        if (topicRefs == null) {
            Log.d("btag", String.format("ModelWatchLevels:getTopics: topicRefs is null"));
            return new ArrayList<>();
        }
        final ArrayList<Topic> topics = new ArrayList<>();
        for (Object t: topicRefs) {
            Topic topic = new Topic(level);
            bindDocRefToTopic((DocumentReference)t, topic);
            topics.add(topic);
        }
        return topics;
    }

    private ArrayList<Lecture> getLectures(ArrayList lectures, Topic topic) {
        if (lectures == null) {
            Log.d("btag", String.format("ModelWatchLevels:getLectures: lectures is null"));
            return new ArrayList<>();
        }
        ArrayList<Lecture> topicLectures = new ArrayList<>();
        for (int i = 0; i < lectures.size(); ++i) {
            Lecture lecture = new Lecture(topic);
            lecture.setOrd(i + 1);
            topicLectures.add(lecture);
            bindDocRefToLecture((DocumentReference)lectures.get(i), lecture);
        }
        return topicLectures;
    }

    private HashMap getExcercises(HashMap<String, Object> excercises, Lecture lecture) {
        if (excercises == null) {
            Log.d("btag", String.format("ModelWatchLevels:getExcercises: excercises is null"));
            return new HashMap();
        }
        HashMap<QuizLevel, ArrayList<Excercise>> res = new HashMap<>();
        for (String key: excercises.keySet()) {
            QuizLevel quizLevel = QuizLevel.valueOf(key);
            ArrayList<Excercise> lectureExcercises = new ArrayList<>();
            for (Object e : (ArrayList)excercises.get(key)) {
                final Excercise excercise = new Excercise(lecture);
                excercise.getId(new CallBack<String>() {
                    @Override
                    public void call(String data) {
                        getDiary(data, new CallBack<Boolean>() {
                            @Override
                            public void call(Boolean data) {
                                excercise.setPassed(data == null ? false : data);
                            }
                        });
                    }
                });
                bindDocRefToExcercise((DocumentReference) e, excercise);
                lectureExcercises.add(excercise);
            }
            res.put(quizLevel, lectureExcercises);
        }
        return res;
    }

    private void getDiary(final String excerciseId, final CallBack<Boolean> cb) {
        if (Backend.inCache("diary/" + excerciseId)) {
            cb.call(((Map<String, Boolean>)Backend.getCache("diary/" + excerciseId)).get(excerciseId));
            return;
        }
        final DocumentReference docRef = FirebaseFirestore
                .getInstance()
                .document(String.format("diary/%s", FirebaseAuth.getInstance().getUid()));
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map passed = (Map)documentSnapshot.getData().get("passed");
                Boolean res = (Boolean)passed.get(excerciseId);
                Backend.putCache("diary/" + excerciseId, passed);
                cb.call(res == null ? false : res);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void bindDocRefToExcercise(final DocumentReference excerciseRef, final Excercise excercise) {
        Backend.putCache(excerciseRef.getId(), excercise);
        excercise.setId(excerciseRef.getId());
        excerciseRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                excercise.setAnswer((String)documentSnapshot.get("answer"));
                Backend.downloadImage("excercises/"
                        + (String) documentSnapshot.get("image")
                        + ".jpg", new CallBack<Bitmap>() {
                    @Override
                    public void call(Bitmap data) {
                        excercise.setImage(data);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", String.format("ModelWatchLevels:onFailure: %s", excerciseRef.getPath()));
            }
        });
    }

    private void bindDocRefToLecture(final DocumentReference lectureRef, final Lecture lecture) {
        Backend.putCache(lectureRef.getId(), lecture);
        lectureRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                lecture.setId(lectureRef.getId());
                lecture.setName((String)documentSnapshot.get("name"));
                lecture.setDescription((String)documentSnapshot.get("description"));
                bindDocRefToResource((DocumentReference) documentSnapshot.get("theory-source"), new CallBack<MediaResource>() {
                    @Override
                    public void call(MediaResource data) {
                        lecture.setResource(data);
                    }
                });
                lecture.setExcercises(getExcercises((HashMap)documentSnapshot.get("excercises"), lecture));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "bindDocRefToLecture onFailure: " + lectureRef.getPath());
            }
        });
    }

    private void bindDocRefToResource(final DocumentReference resourceRef, final CallBack<MediaResource> cb) {
        resourceRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name = (String)documentSnapshot.get("name");
                String type = (String)documentSnapshot.get("type");
                String url = (String)documentSnapshot.get("url");
                MediaResource resource = MediaResourceFactory.produce(name, type, url);
                Backend.putCache(documentSnapshot.getId(), resource);
                cb.call(resource);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

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
    }

    private void bindDocRefToLevel(final DocumentReference levelRef, final Level level) {
        Backend.putCache(levelRef.getId(), level);
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
    }
}