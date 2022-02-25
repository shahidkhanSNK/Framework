package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import Utility.BrowserFactory;
import Utility.ExcelDataProvider;

public class BaseClass {

	WebDriver browser;
	WebDriverWait wait;
	
	
	public BaseClass(WebDriver browserDriver) {
		browser = browserDriver;
		
		wait = new WebDriverWait(browser,10);
	
		
		
	}
	
	
	
}
