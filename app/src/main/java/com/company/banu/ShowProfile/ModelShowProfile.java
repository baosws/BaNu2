package com.company.banu.ShowProfile;

import android.graphics.Bitmap;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;

public class ModelShowProfile {
    public ModelShowProfile() {
    }
    public void getAvatar(CallBack cb) {
        Backend.downloadImage("avatars/"
                + FirebaseAuth.getInstance().getCurrentUser().getUid()
                + ".jpg",
                cb);
    }

    public String getUserName() {
        return FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
    }
}
