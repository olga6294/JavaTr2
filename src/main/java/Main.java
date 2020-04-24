import localdirectory.Directory;
import localdirectory.DirectoryListener;
import file.FileController;

public class Main {

    public static void main(String[] args){
        String ACCESS_TOKEN = args[0];

        FileController fileController = new FileController(ACCESS_TOKEN);
        DirectoryListener directoryListener = new DirectoryListener(fileController, new Directory("src/dropbox-java"));

        directoryListener.directoryListener();
    }
}
