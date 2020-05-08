package file;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import configproperties.ConfigProperties;
import email.SendgridEmailSender;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileController {

    private DbxRequestConfig config;
    private DbxClientV2 client;

    public FileController(String ACCESS_TOKEN){
        config = new DbxRequestConfig("dropbox/java-tutorial");
        client  = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public void uploadFile(String filename, String directory){
        try (InputStream in = new FileInputStream(directory+"/"+filename)) {

            FileMetadata metadata = client.files().uploadBuilder("/"+filename)
                    .uploadAndFinish(in);

            SendgridEmailSender sendgridEmailSender = new SendgridEmailSender(new ConfigProperties("src/config.properties"));
            sendgridEmailSender.sendEmail("opasieczynska@gmail.com", "opasieczynska@gmail.com", "FILE UPDATE", metadata.toStringMultiline());

            System.out.println(metadata.toStringMultiline());
        } catch (UploadErrorException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
        } catch (DbxException ex) {
            System.err.println("DBXError uploading to Dropbox: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
