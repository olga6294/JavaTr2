import com.dropbox.core.DbxException;
import dropboxdirectory.DropboxDirectoryContentProvider;
import fileupdateservice.FileUpdateService;
import localdirectory.DirectoryContentProvider;
import localdirectory.DirectoryListener;
import file.FileController;
import metadata.MetaDataProvider;

public class Main {

    public static void main(String[] args){

        FileController fileController = new FileController(args[0]);
        DirectoryListener directoryListener = new DirectoryListener(fileController, args[1]);
        FileUpdateService fileUpdateService = new FileUpdateService(fileController, args[1]);

        DropboxDirectoryContentProvider dropboxDirectoryContentProvider = new DropboxDirectoryContentProvider(args[0]);

        fileUpdateService.updateFiles(dropboxDirectoryContentProvider.getDropboxContent(), DirectoryContentProvider.provideDirectoryContent(args[1]));

        directoryListener.listen();

        MetaDataProvider.showMetaData();
    }
}
