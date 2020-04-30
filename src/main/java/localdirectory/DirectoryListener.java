package localdirectory;

import file.FileController;
import lombok.AllArgsConstructor;
import metadata.MetaDataCollector;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;

@AllArgsConstructor
public class DirectoryListener {

    private FileController fileController;
    private String directory;

    public void listen() {

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path directoryPath = Paths.get(directory);

            directoryPath.register(watchService, ENTRY_CREATE);

            WatchKey watchKey = watchService.take();

            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {

                    executorService.execute(() -> takeActionOnEvent(watchEvent));

                }
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void takeActionOnEvent(WatchEvent<?> event) {
        WatchEvent.Kind<?> kind = event.kind();

        if (kind.equals(ENTRY_CREATE)) {
            fileController.uploadFile(event.context().toString(), directory);
            System.out.println("created " + event.context());

            MetaDataCollector.getInstance().incrementFilesSent();
        }
    }
}
