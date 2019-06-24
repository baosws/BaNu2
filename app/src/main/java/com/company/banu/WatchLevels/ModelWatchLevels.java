package com.company.banu.WatchLevels;

import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ModelWatchLevels {
    public ModelWatchLevels() {

    }
    public static class Level {
        public String name;
        public String image;
        public int level;

        public Level() {}

        public Level(String name, String image) {
            this.name = name;
            this.image = image;
        }
    }

    public void getLevels(final CallBack<ArrayList<Level>> cb) throws InterruptedException {
        FirebaseFirestore.getInstance().collection("levels").get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    ArrayList<Level> levels = new ArrayList<>();
                    for (DocumentSnapshot ds: queryDocumentSnapshots.getDocuments()) {
                        Level level = ds.toObject(Level.class);
                        levels.add(level);
                    }
                    cb.call(levels);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("btag", "get levels failed");
                }
            });
    }
}
