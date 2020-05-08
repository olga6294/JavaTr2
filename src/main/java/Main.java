import com.dropbox.core.DbxException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import configproperties.ConfigProperties;
import dropboxdirectory.DropboxDirectoryContentProvider;
import email.EmailController;
import email.SendgridEmail;
import fileupdateservice.FileUpdateService;
import localdirectory.DirectoryContentProvider;
import localdirectory.DirectoryListener;
import file.FileController;
import metadata.MetaDataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args){

        ConfigProperties configProperties = new ConfigProperties("src/config.properties");

        FileController fileController = new FileController(configProperties.getProperty("ACCESS_KEY"));
        DirectoryListener directoryListener = new DirectoryListener(fileController, configProperties.getProperty("directory"));
        FileUpdateService fileUpdateService = new FileUpdateService(fileController, configProperties.getProperty("directory"));

        DropboxDirectoryContentProvider dropboxDirectoryContentProvider = new DropboxDirectoryContentProvider(configProperties.getProperty("ACCESS_KEY"));

        fileUpdateService.updateFiles(dropboxDirectoryContentProvider.getDropboxContent(), DirectoryContentProvider.provideDirectoryContent(configProperties.getProperty("directory")));

        Thread thread = new Thread(new MetaDataProvider());
        thread.start();

        directoryListener.listen();

    }
}
