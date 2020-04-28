package metadata;

public class MetaDataCollector {

    private static MetaDataCollector metaDataCollector;

    private int filesSent;

    public static synchronized MetaDataCollector getInstance(){
        if(metaDataCollector == null)
            metaDataCollector = new MetaDataCollector();
        return metaDataCollector;
    }

    public synchronized void incrementFilesSent(){
        filesSent++;
    }

    public int getFilesSent() {
        return filesSent;
    }
}
