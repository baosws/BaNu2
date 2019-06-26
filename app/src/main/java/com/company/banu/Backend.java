package com.company.banu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

import com.company.banu.WatchLectures.LectureItem.Lecture;
import Universe.User;


public class Backend {
    static HashMap<String, Object> cache;
    static {
        cache = new HashMap<>();
    }

    public static ArrayList<Lecture> getLectures() {
        return null;
    }

    public static User getCurrentUser() {
        return null;
    }

    public static void downloadFile(final String filename, final CallBack<byte[]> cb) {
        if (cache.containsKey(filename)) {
            cb.call((byte[])cache.get(filename));
            return;
        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference ref = storage.getReference(filename);
        ref.getBytes(1 << 20).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                if (bytes != null) {
                    Log.d("btag", "file download succeed " + filename + ", byte length " + bytes.length);
                }
                else {
                    Log.w("btag", "file download returned null " + filename);
                }
                cache.put(filename, bytes);
                cb.call(bytes);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("btag", "file download failed " + filename);
                cb.call(null);
            }
        });
    }

    public static void downloadImage(final String filename, final CallBack<Bitmap> cb) {
        downloadFile(filename, new CallBack<byte[]>() {
            public void call(byte[] bytes) {
                Bitmap bitmap = null;
                if (bytes != null) {
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }
                cb.call(bitmap);
            }
        });
    }

    public static void storeCache(String key, Object value) {
        cache.put(key, value);
    }
    public static boolean inCache(String key) {
        return cache.containsKey(key);
    }
    public static Object getCache(String key) {
        return cache.get(key);
    }

    public static void downloadAvatar(CallBack<Bitmap> cb) {
        Backend.downloadImage("avatars/"
        + FirebaseAuth.getInstance().getCurrentUser().getUid()
        + ".jpg",
        cb);
    }
}
