package com.company.banu.ShowProfileSetting;

import android.graphics.Bitmap;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;

public class ModelShowProfileSetting {
    public ModelShowProfileSetting() {
    }

    public String getUserName() {
        return FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
    }

    public String getEmail() {
        return FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }
}
