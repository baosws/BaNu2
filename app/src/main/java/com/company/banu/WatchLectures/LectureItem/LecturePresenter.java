package com.company.banu.WatchLectures.LectureItem;

import com.company.banu.CallBack;

class LecturePresenter {
    LectureView view;
    LectureModel lectureModel;
    LecturePresenter(LectureView view) {
        this.view = view;
        lectureModel = new LectureModel(this);
    }

    void setLecture(Lecture lecture) {
        lectureModel.setLecture(lecture);
    }

    public void rebind() {
        lectureModel.getId(new CallBack<String>() {
            @Override
            public void call(String data) {
                view.setLectureId(data);
            }
        });
        lectureModel.getName(new CallBack<String>() {
            @Override
            public void call(String data) {
                view.setLectureName(data);
            }
        });
        lectureModel.getPercent(new CallBack<Float>() {
            @Override
            public void call(Float data) {
                view.setPercent(data);
            }
        });
    }

    public void getLectureId(CallBack<String> cb) {
        lectureModel.getLectureId(cb);
    }
}
