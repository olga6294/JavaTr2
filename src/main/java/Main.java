import localdirectory.Directory;
import localdirectory.DirectoryListener;
import file.FileController;

public class Main {

    public static void main(String[] args){

        FileController fileController = new FileController(args[0]);
        DirectoryListener directoryListener = new DirectoryListener(fileController, new Directory("src/dropbox-java"));

        directoryListener.directoryListener();
    }
}
