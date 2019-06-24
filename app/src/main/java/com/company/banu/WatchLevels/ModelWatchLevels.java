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
import java.util.List;

import Universe.Lecture;
import Universe.Level;

public class ModelWatchLevels {
    public ModelWatchLevels() {

    }
    ArrayList<Level> levels;

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
                for (Level level: levels) {
                    Log.d("btag", level.name);
                }
                ref.removeEventListener(this);
                m.notify();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("btag", databaseError.getMessage());
                ref.removeEventListener(this);
                m.notify();
            }
        });
        m.wait();
        return levels;
    }
}
