package com.company.banu.WatchLectures.LectureItem;

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
        view.setLectureId(lectureModel.getId());
        view.setLectureName(lectureModel.getName());
        view.setPercent(lectureModel.getPercent());
    }
}
