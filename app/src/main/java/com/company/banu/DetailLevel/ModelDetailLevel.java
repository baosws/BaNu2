package com.company.banu.DetailLevel;

import android.graphics.Bitmap;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.google.firebase.auth.FirebaseAuth;

public class ModelDetailLevel {
    ModelDetailLevel() {

    }

    void getAvatar(CallBack<Bitmap> cb) {
        Backend.downloadImage("avatars/"
                + FirebaseAuth.getInstance().getCurrentUser().getUid()
                + ".jpg", cb);
    }
}
