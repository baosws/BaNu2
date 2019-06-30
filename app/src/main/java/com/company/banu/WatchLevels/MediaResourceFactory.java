package com.company.banu.WatchLevels;

import android.util.Log;

import Universe.ImageResource;
import Universe.VideoResource;

public class MediaResourceFactory {
    public static MediaResource produce(String name, String type, String url) {
        if (type.equalsIgnoreCase("video")) {
            return new VideoResource(name, url);
        }
        else if (type.equalsIgnoreCase("image")) {
            return new ImageResource(name, url);
        }
        else {
            Log.d("btag", String.format("MediaResourceFactory:produce: new type: %s", type));
            return null;
        }
    }
}
