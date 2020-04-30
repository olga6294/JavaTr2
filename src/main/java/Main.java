import com.dropbox.core.DbxException;
import dropboxdirectory.DropboxDirectoryContentProvider;
import fileupdateservice.FileUpdateService;
import localdirectory.DirectoryContentProvider;
import localdirectory.DirectoryListener;
import file.FileController;
import metadata.MetaDataProvider;

public class Main {

    private static final int ACCESS_KEY = 0;
    private static final int DIRECTORY = 1;

    public static void main(String[] args){

        FileController fileController = new FileController(args[ACCESS_KEY]);
        DirectoryListener directoryListener = new DirectoryListener(fileController, args[DIRECTORY]);
        FileUpdateService fileUpdateService = new FileUpdateService(fileController, args[DIRECTORY]);

        DropboxDirectoryContentProvider dropboxDirectoryContentProvider = new DropboxDirectoryContentProvider(args[0]);

        fileUpdateService.updateFiles(dropboxDirectoryContentProvider.getDropboxContent(), DirectoryContentProvider.provideDirectoryContent(args[1]));

        Thread thread = new Thread(new MetaDataProvider());
        thread.run();

        directoryListener.listen();
    }
}
