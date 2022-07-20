package sg.edu.nus.iss.day13workshop.services;

import java.io.File;

public class DatabaseService {
    
    private File dataDir = new File("some directory"); // initiating a place to store files in the directory


    public File getDataDir() {
        return dataDir;
    }

    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    // for mac: /Users/<username>/data
    public boolean isDataDirValid() {
        return dataDir.exists() && dataDir.isDirectory() && dataDir.canWrite();
    }

    
}
