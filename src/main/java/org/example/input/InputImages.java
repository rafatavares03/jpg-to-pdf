package org.example.input;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class InputImages {
    private Queue<File> queue;
    static FilenameFilter filter;

    public InputImages(){
        this.queue = new LinkedList<>();
        filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
            return name.endsWith(".jpg") || name.endsWith("jpeg");
            }
        };
    }

    public void addImages(File directory) {
        File imageFiles[] = directory.listFiles(filter);
        if(imageFiles == null) return;
        Collections.addAll(this.queue, imageFiles);
    }

    public Queue<File> getQueue() {
        return this.queue;
    }
}
