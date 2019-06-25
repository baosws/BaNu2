package com.company.banu.DetailLevel;

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
import java.util.Map;
import java.util.concurrent.Semaphore;

public class ModelDetailLevel {
    ModelDetailLevel() {

    }

    void getAvatar(CallBack<Bitmap> cb) {
        Backend.downloadImage("avatars/"
                + FirebaseAuth.getInstance().getCurrentUser().getUid()
                + ".jpg", cb);
    }

    void getTopics(final CallBack<ArrayList<Topic>> topicCb, final CallBack<Integer> doneCb) {
        final Task<QuerySnapshot> doc = FirebaseFirestore
                .getInstance()
                .collection(String.format("diary/%s/topics",
                        FirebaseAuth.getInstance().getUid())).get();
        final ArrayList<Topic> topics = new ArrayList<>();
        doc.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int i = 0;
                for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {
                    final int id = i++;
                    final Topic t = new Topic();
                    topics.add(t);
                    t.percent = Float.parseFloat(ds.get("percent").toString());
                    Log.d("btag", String.format("onSuccess: percent %f", t.percent));
                    final DocumentReference topic = (DocumentReference) ds.get("topic");

                    topic.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Log.d("btag", "hello");
                            t.name = (String) documentSnapshot.get("name");
                            t.id = topic.getId();
                            doneCb.call(id);
                            Log.d("btag", String.format("onSuccess: id %s, name %s", t.id, t.name));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            doneCb.call(-id);
                            Log.d("btag", "onFailure: topic id " + t.id);
                        }
                    });
                }
                topicCb.call(topics);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "get topics failed ", e);
            }
        });
    }
}
