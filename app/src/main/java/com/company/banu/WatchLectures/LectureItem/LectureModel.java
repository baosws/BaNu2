package com.company.banu.WatchLectures.LectureItem;

import com.company.banu.CallBack;

class LectureModel {
    LecturePresenter presenter;
    Lecture lecture;
    public LectureModel(LecturePresenter lecturePresenter) {
        this.presenter = lecturePresenter;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public void getId(final CallBack<String> cb) {
        lecture.getOrd(new CallBack<Integer>() {
            @Override
            public void call(Integer data) {
                cb.call(data.toString());
            }
        });
    }

    public void getName(CallBack<String> cb) {
        lecture.getName(cb);
    }

    public void getPercent(CallBack<Float> cb) {
        lecture.getPercent(cb);
    }

    public void getLectureId(CallBack<String> cb) {
        lecture.getId(cb);
    }
}
