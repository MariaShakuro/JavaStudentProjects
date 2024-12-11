package org.example.laba6;
import velo.ConcreteVelo;

import java.util.List;

public class XmlWriterThread implements Runnable {
    private final List<ConcreteVelo> veloList;
    private final String filePath;

    public XmlWriterThread(List<ConcreteVelo> veloList, String filePath) {
        this.veloList = veloList;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        XMLWriter.writeBikes(filePath, veloList);
    }
}

