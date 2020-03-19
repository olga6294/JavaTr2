package file;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import localdirectory.Directory;
import metadataprovider.MetaDataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileController {

    private static final String ACCESS_TOKEN = "Qg2qypCGpxAAAAAAAAAAXSKy1K4CM4TSyKhmvEVBxFP2_3sSjf-BGYpRg3j4Ck6q";

    private DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial");
    private DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    private FileService fileService;

    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    public void uploadFile(String filename, String directory){
        File file = fileService.upload(filename, directory);

        try (InputStream in = new FileInputStream(file.getPath())) {
            FileMetadata metadata = client.files().uploadBuilder("/"+file.getTitle())
                    .uploadAndFinish(in);
            MetaDataProvider.incrementCountSentFiles();
        }catch(UploadErrorException uploadErrorException){
            uploadErrorException.printStackTrace();
            throw new RuntimeException();
        }catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
            throw new RuntimeException();
        }catch(IOException ioException){
            ioException.printStackTrace();
            throw new RuntimeException();
        }catch(DbxException dbxException){
            dbxException.printStackTrace();
            throw new RuntimeException();
        }
    }

}
