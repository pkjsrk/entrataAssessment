package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
	
	
	public static String getProperty(String data) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\pmanikpu\\OneDrive - Capgemini\\Desktop\\Eclipse\\Pandi\\entrata\\src\\test\\java\\utility\\config.properties");
			
		prop.load(fis);
		
		return prop.getProperty(data);
	}

}
