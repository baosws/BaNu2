package com.company.banu.WatchLevels;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.WatchLevels.LevelItem.Level;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.BitSet;


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
        final Task<QuerySnapshot> doc = FirebaseFirestore
                .getInstance()
                .collection(String.format("diary/%s/levels",
                        FirebaseAuth.getInstance().getUid())).orderBy("ord").get();

        final ArrayList<Level> levels = new ArrayList<>();
        doc.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {
                    final Level t = new Level();
                    levels.add(t);
                    t.percent = Float.parseFloat(ds.get("percent").toString());
                    Log.d("btag", String.format("onSuccess: percent %f", t.percent));
                    final DocumentReference levelRef = (DocumentReference) ds.get("level");

                    levelRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            t.name = (String) documentSnapshot.get("name");
                            t.level = Integer.parseInt(documentSnapshot.get("level").toString());
                            t.id = levelRef.getId();
                            Backend.downloadImage("levels/"
                                    + t.id
                                    + ".jpg", new CallBack<Bitmap>() {
                                @Override
                                public void call(Bitmap data) {
                                    t.image = data;
                                    t.notifyDone();
                                }
                            });

                            Log.d("btag", String.format("onSuccess: id %s, name %s", t.id, t.name));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("btag", "onFailure: topic id " + t.id);

                            t.notifyDone();
                        }
                    });
                }
                Backend.storeCache("levels", levels);
                cb.call(levels);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "get topics failed ", e);
            }
        });
    }
}
