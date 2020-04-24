package localdirectory;

import file.FileController;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryListener {

    private FileController fileController;
    private Directory directory;

    public DirectoryListener(FileController fileController, Directory directory){
        this.fileController = fileController;
        this.directory = directory;
    }

    public void directoryListener(){

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path directoryPath = Paths.get(directory.getPath());

            directoryPath.register(watchService, ENTRY_CREATE);

            WatchKey watchKey = watchService.take();

            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while(true){
                for(WatchEvent<?> watchEvent : watchKey.pollEvents()){

                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {

                    takeActionOnEvent(watchEvent);

                        }
                    });

                    executorService.shutdown();
                }
            }

        }catch(IOException ioException){
            ioException.printStackTrace();
            throw new RuntimeException();
        }catch(InterruptedException interruptedException){
            interruptedException.printStackTrace();
            throw new RuntimeException();
        }
    }
    private void takeActionOnEvent(WatchEvent<?> event){

        WatchEvent.Kind<?> kind = event.kind();

        if(kind.equals(ENTRY_CREATE)){
            fileController.uploadFile(event.context().toString(), directory.getPath());
            System.out.println("created "+event.context());
        }

    }
}
