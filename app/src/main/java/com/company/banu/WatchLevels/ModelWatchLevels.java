package com.company.banu.WatchLevels;

import java.util.ArrayList;


public class ModelWatchLevels {
    public static class Level{
        public String levelName;
        public String levelImage;

        public Level(String name, String image)
        {
            this.levelName = name;
            this.levelImage = image;
        }
    }

    public ModelWatchLevels() {

    }
    ArrayList<Level> levels;
    ModelWatchLevels init() {
//        levels = Backend.getLectures();
        return this;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }
}
