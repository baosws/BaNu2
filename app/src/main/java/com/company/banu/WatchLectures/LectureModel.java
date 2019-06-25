package com.company.banu.WatchLectures;

class LectureModel {
    LecturePresenter presenter;
    Lecture lecture;
    public LectureModel(LecturePresenter lecturePresenter) {
        this.presenter = lecturePresenter;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getId() {
        return lecture.id.toString();
    }

    public String getName() {
        return lecture.name;
    }

    public float getPercent() {
        return lecture.percent;
    }
}
