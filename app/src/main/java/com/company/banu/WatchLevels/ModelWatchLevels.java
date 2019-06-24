package com.company.banu.WatchLevels;

import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ModelWatchLevels {
    public ModelWatchLevels() {

    }
    public static class Level {
        public String name;
        public String image;
        public int level;
        public Level(String name, String image) {
            this.name = name;
            this.image = image;
        }
    }
    ArrayList levels;

    public ArrayList<Level> getLevels() throws InterruptedException {
        if (levels != null) {
            return levels;
        }
        final ModelWatchLevels m = this;
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("levels");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                levels = dataSnapshot.getValue(ArrayList.class);
                for (Object level: levels) {
                    Log.d("btag", ((Level)level).name);
                }
                ref.removeEventListener(this);
                m.notify();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("btag", databaseError.getMessage());
                ref.removeEventListener(this);
            }
        });
        m.wait();
        return levels;
    }
}
