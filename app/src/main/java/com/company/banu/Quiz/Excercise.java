package com.company.banu.Quiz;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class Excercise extends Notifier<ExcerciseEvent> {
    private String id;
    private Bitmap image;
    private String answer;
    private Boolean passed;
    private Lecture lecture;
    public Excercise(Lecture lecture) {
        this.lecture = lecture;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        notify(ExcerciseEvent.HadAnswer);
    }

    public void setImage(Bitmap image) {
        this.image = image;
        notify(ExcerciseEvent.HadImage);
    }

    public void getAnswer(final CallBack<String> cb) {
        addEvent(ExcerciseEvent.HadAnswer, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(answer);
            }
        });
    }

    public void getImage(final CallBack<Bitmap> cb) {
        addEvent(ExcerciseEvent.HadImage, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(image);
            }
        });
    }

    public void setId(String id) {
        this.id = id;
        notify(ExcerciseEvent.HadId);
    }

    public void getId(final CallBack<String> cb) {
        addEvent(ExcerciseEvent.HadId, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(id);
            }
        });
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
        notify(ExcerciseEvent.HadPassed);
    }

    public void getPassed(final CallBack<Boolean> cb) {
        addEvent(ExcerciseEvent.HadPassed, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(passed);
            }
        });
    }

    public Excercise bind(final DocumentReference excerciseRef) {
        final Excercise excercise = this;
        Backend.putCache(excerciseRef.getId(), excercise);
        excercise.setId(excerciseRef.getId());
        excerciseRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                excercise.getId(new CallBack<String>() {
                    @Override
                    public void call(String data) {
                        Log.d("btag", String.format("ModelWatchLevels:call: new excercise %s", data));
                    }
                });
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
        return this;
    }
}
