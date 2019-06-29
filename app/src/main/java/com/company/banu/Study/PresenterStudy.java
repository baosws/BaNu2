package com.company.banu.Study;

import com.company.banu.CallBack;
import com.company.banu.WatchLectures.LectureItem.Lecture;

public class PresenterStudy {
    StudyView view;
    ModelStudy modelStudy;
    public PresenterStudy(StudyView view, Lecture lecture) {
        this.view = view;
        modelStudy = new ModelStudy(lecture);
        init();
    }

    private void init() {
        view.initUI(modelStudy.getLecture());
        modelStudy.getLectureName(new CallBack<String>() {
            @Override
            public void call(String data) {
                view.setTitle(data);
            }
        });
    }
}
