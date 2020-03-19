package localdirectory;

import file.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryContentProvider {

    public static List<File> provideDirectoryContent(Directory directory){
        try(Stream<Path> pathWalker = Files.walk(Paths.get(directory.getPath()))){
            return pathWalker.map(Path::toFile).map(file -> new File(file.getName(), file.length(), file.getPath())).collect(Collectors.toList());
        }catch(IOException ioException){
            ioException.printStackTrace();
            throw new RuntimeException();
        }
    }


}
