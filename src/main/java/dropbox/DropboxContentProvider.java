package dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderErrorException;

public class DropboxContentProvider {

    DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial");
    DbxClientV2 client;

    public DropboxContentProvider(String ACCESS_TOKEN){
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public void listDirectoryContent(String path) {
        try {
            System.out.println(client.files().listFolder(path));
        }catch(ListFolderErrorException listFolderErrorException){
            listFolderErrorException.printStackTrace();
            throw new RuntimeException();
        }catch(DbxException dbxException){
            dbxException.printStackTrace();
            throw new RuntimeException();
        }
    }
}
