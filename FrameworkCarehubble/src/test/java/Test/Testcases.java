package Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utility.BrowserFactory;
import Utility.ConfigDataProvider;
import Utility.ExcelDataProvider;
import Utility.Helper;
import pages.BaseClass;
import pages.Header;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.SecurityCodeScreen;

public class Testcases{

	//WebDriver driver = null;
	WebDriver browser;
	WebDriverWait wait;
	ConfigDataProvider config;
	ExcelDataProvider excel;
	ExtentReports report;
	ExtentTest logger;


	@BeforeSuite
	public void setupsuite() {
		
		Reporter.log("Setting up reports and Test is getting ready", true);

	    config = new ConfigDataProvider();
		excel = new ExcelDataProvider();
		
		ExtentSparkReporter extent = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/CareHubble_"+Helper.getCurrentDateTime()+".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		
		Reporter.log("Setting done and Test can be started", true);

	}
	
	@BeforeTest
	public void setuptest() {
		
		Reporter.log("Trying to start browser and getting application ready", true);

		//ConfigDataProvider config = new ConfigDataProvider();
		browser = BrowserFactory.startApplication(browser, config.getBrowser(), config.getQaUrl());
		System.out.println(browser.getTitle());
		
		Reporter.log("Browser and Appplication is up and running", true);

	}
	

	@Test(priority = 0)
	public void loginwithInvalidEmail() throws InterruptedException {

		// log in
		logger = report.createTest("Login with invalid email");
		
		LoginPage loginpageobj = new LoginPage(browser);
		
		logger.info("Negative Test Case - Entering invalid email");
		//loginpageobj.setEmailAddress("shahid+257@troontechnologies.comm");
		loginpageobj.setEmailAddress(excel.getStringData("Login", 0, 0));
		loginpageobj.setPassword(excel.getStringData("Login", 0, 1));
		loginpageobj.clickSignInButton();
		String loginErrorMessage = loginpageobj.getErrorMessage();
		String expectedError = "Error! The email or password is incorrect";
		Assert.assertEquals(loginErrorMessage, expectedError);
		System.out.println("Error Message Appeared - Test Passed\n");
		logger.pass("Test passed - error message appeared");
	}
//	
//	@Test(priority = 1)
//	public void loginwithInvalidPassword() throws InterruptedException {
//
//		// log in
//		LoginPage loginpageobj = new LoginPage(browser);
//		loginpageobj.setEmailAddress("shahid+257@troontechnologies.com");
//		loginpageobj.setPassword("12345Qwe!@@");
//		loginpageobj.clickSignInButton();
//		String loginErrorMessage = loginpageobj.getErrorMessage();
//		String expectedError = "Error! The email or password is incorrect";
//		Assert.assertEquals(loginErrorMessage, expectedError);
//		System.out.println("Error Message Appeared - Test Passed\n");
//	}
//	
//	@Test(priority = 2)
//	public void loginwithEmptyFields() throws InterruptedException {
//
//		// log in
//		LoginPage loginpageobj = new LoginPage(browser);
//		loginpageobj.setEmailAddress("");
//		loginpageobj.setPassword("");
//		loginpageobj.clickSignInButton();
//		String loginValidationMessage = loginpageobj.getValidationMessage();
//		String expectedMessage = "Email is a required field";
//		Assert.assertEquals(loginValidationMessage, expectedMessage);
//		System.out.println("Validation Message Appeared - Test Passed\n");
//	}
//
//	@Test(priority = 3)
//	public void loginwithValidData() throws InterruptedException {
//
//		// log in
//		LoginPage loginpageobj = new LoginPage(browser);
//		loginpageobj.setEmailAddress("shahid+257@troontechnologies.com");
//		loginpageobj.setPassword("12345Qwe!@");
//		loginpageobj.clickSignInButton();
//		String pageTitleText = loginpageobj.getPageText();
//		String expectedText = "Sign In: CareHubble";
//		Assert.assertEquals(pageTitleText, expectedText);
//		System.out.println("Navigated to Security Code Screen Successfully - Test Passed\n");
//	}
//
//
//	@Test(priority = 4)
//	public void securitycode() throws InterruptedException {
//
//		// Enter security code and click continue button
//		SecurityCodeScreen sescuritycodepageobj = new SecurityCodeScreen(browser);
//		List<WebElement> codetest = sescuritycodepageobj.getSecurityCodeTextBoxes();
//		codetest.get(0).sendKeys("0");
//		codetest.get(1).sendKeys("0");
//		codetest.get(2).sendKeys("0");
//		codetest.get(3).sendKeys("0");
//		Thread.sleep(4000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
//		//sescuritycodepageobj.setCodeOne("0");
//		//sescuritycodepageobj.setCodeTwo("0");
//		//sescuritycodepageobj.setCodeThree("0");
//		//sescuritycodepageobj.setCodeFour("0");
//		sescuritycodepageobj.clickContinueButton();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.icon-Dashboard-Unfilled")));
////		if (browser.findElement(By.cssSelector("span.icon-Dashboard-Unfilled")).isDisplayed()) {
////			System.out.println("Customer login is Successful - Test Passed\n");
////			}
////			else {
////			System.out.println("Customer login is Unsuccessful - Test Failed\n");
////			}
//		String loginDashboardPageTitleText = sescuritycodepageobj.getDashboardPageTitle();
//		String expectedText = "Dashboard: CareHubble";
//		Assert.assertEquals(loginDashboardPageTitleText, expectedText);
//		System.out.println("Customer login is Successful - Test Passed\n");
//	}
//	
//	@Test(priority = 5)
//	public void headerDropdown() throws InterruptedException {
//		Header headerclassobj = new Header(browser);
//		headerclassobj.headerDropdownArrow();
//		
//	}
//	
//	@Test(priority = 6)
//	public void profilemenuoption() throws InterruptedException {
//		MyProfilePage myprofilepageobj = new MyProfilePage(browser);
//		myprofilepageobj.headerDropdownMyProfileOption();
//		Thread.sleep(6000);
//		
//	}
	
	@AfterTest
	public void teardowntest() {
//		browser.close();
//		browser.quit();
		BrowserFactory.quitBrowser(browser);

	}

	@AfterMethod
	public void teardownMethod(ITestResult result) 
	{
		Reporter.log("Test is about to end", true);
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.fail("Test Failed ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(browser)).build());
		}
		
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass("Test Passed ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(browser)).build());
		}
		report.flush();
		
		Reporter.log("Test Completed and Reports Generated", true);


	}


}
