package com.company.banu.Study;

import android.app.Activity;

public class PresenterStudy {
    Activity activity;
    ModelStudy modelStudy;
    public PresenterStudy(Activity activity) {
        this.activity = activity;
        modelStudy = new ModelStudy();
    }

    public void bindLevelsToView() {

    }
}
