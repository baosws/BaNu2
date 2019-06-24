package com.company.banu.WatchTopics;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ModelWatchTopics {
    public ModelWatchTopics() {

    }
    public static class Topic {
        public String name;
        public String image;
        public int level;
        public Topic(String name, String image) {
            this.name = name;
            this.image = image;
        }
    }
}
