package com.company.banu.WatchLectures.LectureItem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.telecom.Call;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.Quiz.Excercise;
import com.company.banu.Quiz.QuizLevel;
import com.company.banu.WatchLevels.MediaResource;
import com.company.banu.WatchLevels.MediaResourceFactory;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Lecture extends Notifier<LectureEvent> {
    private Topic topic;
    private String name;
    private String id;
    private Integer ord;
    private HashMap<QuizLevel, ArrayList<Excercise>> excercises;
    private String description;
    private MediaResource resource;
    private DocumentReference ref;
    private Semaphore semaphore;
    private int passedCount = 0;

    public Lecture(Topic topic)
    {
        this.topic = topic;
        semaphore = new Semaphore(1);
        notify(LectureEvent.HadPercent);
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

    public void getPercent(final CallBack<Float> cb) {
        addEvent(LectureEvent.HadPercent, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                getExcercises(new CallBack<HashMap<QuizLevel, ArrayList<Excercise>>>() {
                    @Override
                    public void call(HashMap<QuizLevel, ArrayList<Excercise>> data) {
                        int total = 0;
                        for (QuizLevel quizLevel: data.keySet()) {
                            total += data.get(quizLevel).size();
                        }
                        cb.call(total == 0 ? 0 : passedCount * 1f / total);
                    }
                });
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

    public void setExcercises(HashMap<QuizLevel, ArrayList<Excercise>> excercises) {
        this.excercises = excercises;
        notify(LectureEvent.HadExcercises);
    }

    public void getExcercises(final CallBack<HashMap<QuizLevel, ArrayList<Excercise>>> cb) {
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

    public Lecture bind(final DocumentReference lectureRef) {
        this.ref = lectureRef;
        final Lecture lecture = this;
        Backend.putCache(lectureRef.getId(), lecture);
        lectureRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                lecture.setId(lectureRef.getId());
                lecture.setName((String)documentSnapshot.get("name"));
                lecture.setDescription((String)documentSnapshot.get("description"));
                MediaResourceFactory.produce((DocumentReference) documentSnapshot.get("theory-source"), new CallBack<MediaResource>() {
                    @Override
                    public void call(MediaResource resource) {
                        lecture.setResource(resource);
                    }
                });
                lecture.setExcercises(getExcercises((HashMap)documentSnapshot.get("excercises"), lecture));
            }
        });
        return this;
    }

    private static HashMap<QuizLevel, ArrayList<Excercise>> getExcercises(HashMap<String, Object> excercises, Lecture lecture) {
        if (excercises == null) {
            Log.d("btag", String.format("ModelWatchLevels:getExcercises: excercises is null"));
            return new HashMap();
        }
        HashMap<QuizLevel, ArrayList<Excercise>> res = new HashMap<>();
        for (String key: excercises.keySet()) {
            QuizLevel quizLevel = QuizLevel.valueOf(key);
            ArrayList<Excercise> lectureExcercises = new ArrayList<>();
            for (Object e : (ArrayList)excercises.get(key)) {
                final Excercise excercise = new Excercise(lecture).bind((DocumentReference) e);
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
                lectureExcercises.add(excercise);
            }
            res.put(quizLevel, lectureExcercises);
        }
        return res;
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
                    return;
                }
                if (documentSnapshot.getData() == null) {
                    return;
                }
                ArrayList passed = (ArrayList) documentSnapshot.getData().get("passed");
                if (passed == null) {
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

    public void updatePassedCount() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passedCount++;
        semaphore.release();
        notify(LectureEvent.HadPercent);
    }
}
