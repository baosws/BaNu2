package com.company.banu.WatchLevels;

import com.company.banu.Backend;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Universe.Lecture;

public class Model {
    public Model() {

    }
    ArrayList<Lecture> levels;
    Model init() {
        levels = Backend.getLectures();
        return this;
    }

    public ArrayList<Lecture> getLevels() {
        return levels;
    }
}
