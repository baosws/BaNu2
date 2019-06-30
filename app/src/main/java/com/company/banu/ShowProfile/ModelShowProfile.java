package com.company.banu.ShowProfile;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.google.firebase.auth.FirebaseAuth;

public class ModelShowProfile {
    PresenterShowProfile presenter;

    public ModelShowProfile(PresenterShowProfile presenter) {
        this.presenter = presenter;
    }
    public void getAvatar(CallBack cb) {
        Backend.downloadAvatar(cb);
    }

    public String getUserName() {
        return FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
    }

    public String getEmail() {
        return FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }
}
