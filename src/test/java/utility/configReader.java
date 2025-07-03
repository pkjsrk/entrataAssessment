package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {

    public static String getProperty(String data) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        return prop.getProperty(data);
    }
}