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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class Excercise extends Notifier<ExcerciseEvent> {
    private String id;
    private Bitmap image;
    private String answer;
    private Boolean passed = false;
    private DocumentReference ref;
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
        if ((this.passed == false && passed == true)) {
            lecture.updatePassedCount();
            Map<String, Object> newData = new HashMap<>();
            newData.put("passed", FieldValue.arrayUnion(ref));
            FirebaseFirestore
                    .getInstance()
                    .collection("diary")
                    .document(FirebaseAuth.getInstance().getUid())
                    .update(newData);
        }
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
        this.ref = excerciseRef;
        final Excercise excercise = this;
        Backend.putCache(excerciseRef.getId(), excercise);
        excercise.setId(excerciseRef.getId());
        excerciseRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
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
        });
        return this;
    }
}
