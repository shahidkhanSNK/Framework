package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConfigDataProvider {
	
	Properties pro;
	
	public ConfigDataProvider() 
	{
		File src = new File("./Config/Config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			
			pro = new Properties();
			
			pro.load(fis);
		} 
		catch (Exception e) {
			
			System.out.println("Unable to load Config File >>"+e.getMessage());
		}
	}
	
	public String getDataFromConfig(String keyToSearch)
	{
		return pro.getProperty(keyToSearch);
	}
	
	public String getBrowser()
	{
		return pro.getProperty("Browser");
	}
	
	public String getQaUrl()
	{
		return pro.getProperty("qaUrl");
	}

}
