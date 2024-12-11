package org.example.laba6;

import velo.ConcreteVelo;

import java.util.List;

public class JsonWriterThread implements Runnable {
    private final List<ConcreteVelo> veloList;
    private final String filePath;

    public JsonWriterThread(List<ConcreteVelo> veloList, String filePath) {
        this.veloList = veloList;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        JSONWriter.writeBikes(filePath, veloList);
    }
}

