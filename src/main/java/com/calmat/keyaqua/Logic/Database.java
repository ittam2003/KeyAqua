package com.calmat.keyaqua.Logic;

import java.io.FileWriter;
import java.io.IOException;

public class Database {

    private String fileName;

    public Database(String fileName) {
        this.fileName = fileName + ".dat";
    }
    public void writeKeyToFile(String string) throws IOException {
        FileWriter writer = null;
        writer = new FileWriter("Keys/"+ this.fileName);
        writer.write(string);
        writer.close();
    }
}
