package file;

import localdirectory.Directory;
import localdirectory.DirectoryContentProvider;

import java.util.stream.Collectors;

public class FileService {

    public File upload(String filename, String directory){
        return DirectoryContentProvider.provideDirectoryContent(new Directory(directory)).stream().filter(file -> file.getTitle().equals(filename)).collect(Collectors.toList()).get(0);
    }

}
