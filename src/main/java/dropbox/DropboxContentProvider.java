package dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import file.File;

import java.util.List;

public class DropboxContentProvider {

    String ACCESS_TOKEN = "Qg2qypCGpxAAAAAAAAAAXSKy1K4CM4TSyKhmvEVBxFP2_3sSjf-BGYpRg3j4Ck6q";

    DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial");
    DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    public List<File> listDirectoryContent(){
        return null;
    }
}
