package com.company.banu.WatchLevels;

import com.company.banu.Backend;

import java.util.ArrayList;

import Universe.Lecture;

public class ModelWatchLevels {
    public ModelWatchLevels() {

    }
    ArrayList<Lecture> levels;
    ModelWatchLevels init() {
        levels = Backend.getLectures();
        return this;
    }

    public ArrayList<Lecture> getLevels() {
        return levels;
    }
}
