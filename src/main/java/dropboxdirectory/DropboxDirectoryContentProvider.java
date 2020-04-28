package dropboxdirectory;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class DropboxDirectoryContentProvider {

    private DbxRequestConfig config;
    private DbxClientV2 client;

    public DropboxDirectoryContentProvider(String ACCESS_TOKEN){
        config = new DbxRequestConfig("dropbox/java-tutorial");
        client  = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public List<String> getDropboxContent(){
        try {
            ListFolderBuilder listFolderBuilder = client.files().listFolderBuilder("");
            return listFolderBuilder.start().getEntries().stream().map(element -> element.getName()).collect(Collectors.toList());
        }catch(DbxException dbxException){
            System.out.println(dbxException.getMessage());
        }
        return null;
    }
}
