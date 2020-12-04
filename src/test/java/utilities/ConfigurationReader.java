package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {

        String path = "configuration.properties";

        try {
            FileInputStream file = new FileInputStream(path);
            properties =  new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println("configuration.properties file bulunamadi.");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


}
