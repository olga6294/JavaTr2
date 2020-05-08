package configproperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    private Properties properties;

    public ConfigProperties(String file){
        try {
            properties = new Properties();
            FileInputStream propertiesFile = new FileInputStream(file);
            properties.load(propertiesFile);
            propertiesFile.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            throw new RuntimeException();
        }
    }

    public String getProperty(String property){
        return properties.getProperty(property);
    }
}
