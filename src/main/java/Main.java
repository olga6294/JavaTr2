import localdirectory.Directory;
import localdirectory.DirectoryListener;
import file.FileController;
import file.FileService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args){
        FileController fileController = new FileController(new FileService());
        DirectoryListener directoryListener = new DirectoryListener(fileController, new Directory("src/dropbox-java"));

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                directoryListener.directoryListener();
            }
        });
        executorService.shutdown();
    }
}
