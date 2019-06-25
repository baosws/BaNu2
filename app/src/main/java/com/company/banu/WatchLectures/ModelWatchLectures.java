package com.company.banu.WatchLectures;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.DetailLevel.DiaryTopic.Topic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ModelWatchLectures {
    PresenterWatchLectures presenter;
    ModelWatchLectures(PresenterWatchLectures presenterWatchLectures) {
        this.presenter = presenterWatchLectures;
    }

    public void getLectures(final CallBack<ArrayList<Lecture>> cb) {
        if (Backend.inCache("lectures")) { // TODO
            cb.call((ArrayList<Lecture>) Backend.getCache("lectures"));
            return;
        }
        final Task<QuerySnapshot> doc = FirebaseFirestore
                .getInstance()
                .collection(String.format("diary/%s/lectures",
                        FirebaseAuth.getInstance().getUid())).get();
        final ArrayList<Lecture> lectures = new ArrayList<>();
        doc.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {
                    final Lecture t = new Lecture();
                    lectures.add(t);
                    t.percent = Float.parseFloat(ds.get("percent").toString());
                    Log.d("btag", String.format("onSuccess: percent %f", t.percent));
                    final DocumentReference topic = (DocumentReference) ds.get("lecture");

                    topic.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            t.name = (String) documentSnapshot.get("name");
                            t.id = topic.getId();
                            t.notifyDone();
                            Log.d("btag", String.format("onSuccess: id %s, name %s", t.id, t.name));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            t.notifyDone();

                            Log.d("btag", "onFailure: lecture id " + t.id);
                        }
                    });
                }
                Backend.storeCache("lectures", lectures);
                cb.call(lectures);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "get lectures failed ", e);
            }
        });
    }
}
