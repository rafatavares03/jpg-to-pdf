package com.rafatavares03.model;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ImageLoader {
    private final FilenameFilter filter;

    public ImageLoader() {
        this.filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".jpeg");
            }
        };
    }

    public Queue<File> loadImages(File directory) {
        Queue<File> images = new LinkedList<>();
        File[] files = directory.listFiles(filter);

        if(files != null) {
            Collections.addAll(images, files);
        }

        return images;
    }
}
