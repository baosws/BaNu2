package com.company.banu.WatchLevels;

import android.util.Log;

import com.company.banu.CallBack;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import Universe.ImageResource;
import Universe.VideoResource;

public class MediaResourceFactory {
    public static void produce(DocumentReference resourceRef, final CallBack<MediaResource> cb) {
        resourceRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String type = (String) documentSnapshot.get("type");
                String name = (String) documentSnapshot.get("name");
                String url = (String) documentSnapshot.get("url");
                if (type.equalsIgnoreCase("video")) {
                    cb.call(new VideoResource(name, url));
                }
                else if (type.equalsIgnoreCase("image")) {
                    cb.call(new ImageResource(name, url));
                }
                else {
                    Log.d("btag", String.format("MediaResourceFactory:produce: new type: %s", type));
                    cb.call(null);
                }
            }
        });
    }
}
