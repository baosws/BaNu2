package com.company.banu.WatchLevels;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
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
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.functions.FirebaseFunctions;

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
                    final Level level = new Level().bind(doc.getReference());
                    levels.add(level);
                }
                Backend.putCache("levels", levels);
                cb.call(levels);
            }
        });
    }

    public void enableNewLevel(final Level level) {
        level.getId(new CallBack<String>() {
            @Override
            public void call(String id) {
                Log.d("btag", String.format("ModelWatchLevels:call: uid = %s, levelID = %s",
                        FirebaseAuth.getInstance().getUid(),
                        id));
                Map<String, Object> params = new HashMap<>();
                params.put("uid", FirebaseAuth.getInstance().getUid());
                params.put("levelID", id);
                FirebaseFunctions
                        .getInstance()
                        .getHttpsCallable("addDiaryLevel")
                        .call(params);
                level.setPassed(true);
            }
        });
    }
}