package Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserFactory {

	static WebDriver browser;
	static WebDriverWait wait;
	static String projectPath = System.getProperty("user.dir");
	//System.out.println("projectPath: "+projectPath);
	
	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL)
	{
		if(browserName.equals("Chrome"))
		{
//			String projectPath = System.getProperty("user.dir");
//			System.out.println("projectPath: "+projectPath);

			// To set the property for Google Chrome drive, without it, it will throw an error
			System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/DriverChrome/chromedriver.exe");
			browser = new ChromeDriver();
		}
		else if(browserName.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", projectPath+"/Drivers/FirefoxDriver/geckodriver.exe");
			browser = new FirefoxDriver();
		}
		else if(browserName.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", projectPath+"/Drivers/IEDriver/IEDriverServer.exe");
			browser = new InternetExplorerDriver();
		}
		else
		{
			System.out.println("We do not support this browser");
		}

		wait = new WebDriverWait(browser, 10);
		browser.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		// To maximize the browser window
		browser.manage().window().maximize();

		// Go to CareHubble FrontEnd Portal
		//browser.get("https://qa.carehubble.com/signin");
		browser.get(appURL);
		return browser;
	}
	
	public static void quitBrowser(WebDriver browser)
	{browser.quit();
	}
}
