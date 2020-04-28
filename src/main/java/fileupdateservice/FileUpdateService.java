package fileupdateservice;

import file.FileController;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class FileUpdateService {

    private FileController fileController;
    private String directory;

    public void updateFiles(List<String> localDirectoryContent, List<String> dropboxDirectoryContent){
        if(localDirectoryContent.remove(dropboxDirectoryContent)){
            for(String file : localDirectoryContent){
                fileController.uploadFile(file, directory);
            }
        }
    }
}
