package Test;

import java.awt.AWTException;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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
import pages.OtherDocuments;
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

	@Test(priority = 1)
	public void loginwithInvalidPassword() throws InterruptedException {

		// log in
		logger = report.createTest("Login with Invalid Password");
		LoginPage loginpageobj = new LoginPage(browser);
		logger.info("Negative Test Case - Entering invalid Password");

		loginpageobj.setEmailAddress(excel.getStringData("Login", 1, 0));
		loginpageobj.setPassword(excel.getStringData("Login", 1, 1));
		loginpageobj.clickSignInButton();
		String loginErrorMessage = loginpageobj.getErrorMessage();
		String expectedError = "Error! The email or password is incorrect";
		Assert.assertEquals(loginErrorMessage, expectedError);
		System.out.println("Error Message Appeared - Test Passed\n");
		logger.pass("Test passed - error message appeared");
	}

	@Test(priority = 2)
	public void loginwithEmptyFields() throws InterruptedException {

		// log in
		logger = report.createTest("Login with Empty Fields");
		LoginPage loginpageobj = new LoginPage(browser);
		logger.info("Negative Test Case - Click on Sign in button by leaving required fields empty");
		loginpageobj.setEmailAddress(excel.getStringData("Login", 2, 0));
		loginpageobj.setPassword(excel.getStringData("Login", 2, 1));
		loginpageobj.clickSignInButton();
		String loginValidationMessage = loginpageobj.getValidationMessage();
		String expectedMessage = "Email is a required field";
		Assert.assertEquals(loginValidationMessage, expectedMessage);
		System.out.println("Validation Message Appeared - Test Passed\n");
		logger.pass("Test passed - Validation message appeared");
	}

	@Test(priority = 2)
	public void trytologin() throws InterruptedException, AWTException, NoSuchElementException, ElementNotVisibleException, ElementNotSelectableException, TimeoutException {

		// log in
		logger = report.createTest("Trying to Login");
		LoginPage loginpageobj = new LoginPage(browser);
		logger.info("Negative Test Case - Trying to Login by Entering data in the Fields");
		loginpageobj.setEmailAddress(excel.getStringData("Login", 3, 0));
		loginpageobj.setPassword(excel.getStringData("Login", 3, 1));
		//try{
			loginpageobj.clickSignInButton();

			try {
			String pageHeadingText = loginpageobj.getHeadingText();
			String expectedText = "Add Security Code";
			Assert.assertEquals(pageHeadingText, expectedText);

			System.out.println("User navigated to Enter Security Code Screen - Test Passed\n");
			//logger.pass("Test Passes - Navigated to Security Code Screen");
		}
		catch(Exception exp) {
			System.out.println("User not navigated to Enter Security Code Screen - Test Failed\n "+exp.getMessage());
			logger.fail("Test Failed - Error message appeared " +exp);
			logger.info("Exception is : " +exp.getMessage());
			logger.info("Cause is : " +exp.getCause());
			logger.info("Path is : " +exp.getStackTrace());
			exp.printStackTrace();
		}

	}

	@Test(priority = 3)
	public void loginwithValidData() throws InterruptedException {

		// log in
		logger = report.createTest("Trying to Login with Valid Data");
		LoginPage loginpageobj = new LoginPage(browser);
		logger.info("Negative Test Case - Trying to Login by Entering Valid data in the Fields");
		loginpageobj.setEmailAddress(excel.getStringData("Login", 4, 0));
		loginpageobj.setPassword(excel.getStringData("Login", 4, 1));
		loginpageobj.clickSignInButton();
		String pageTitleText = loginpageobj.getPageText();
		String expectedText = "Sign In: CareHubble";
		Assert.assertEquals(pageTitleText, expectedText);
		System.out.println("Navigated to Security Code Screen Successfully - Test Passed\n");
		logger.pass("Test passed - Navigated to Security Code Screen Successfully");
	}

	
		@Test(priority = 4)
		public void securitycode() throws InterruptedException {
	
			// Enter security code and click continue button
			logger = report.createTest("Enter Security Code");
			SecurityCodeScreen sescuritycodepageobj = new SecurityCodeScreen(browser);
			logger.info("Positive Test Case - Entering Security Code and Navigate to Dashboard");
			List<WebElement> codetest = sescuritycodepageobj.getSecurityCodeTextBoxes();
			codetest.get(0).sendKeys("0");
			codetest.get(1).sendKeys("0");
			codetest.get(2).sendKeys("0");
			codetest.get(3).sendKeys("0");
			Thread.sleep(5000);
			sescuritycodepageobj.clickContinueButton();
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.icon-Dashboard-Unfilled")));
	////		if (browser.findElement(By.cssSelector("span.icon-Dashboard-Unfilled")).isDisplayed()) {
	////			System.out.println("Customer login is Successful - Test Passed\n");
	////			}
	////			else {
	////			System.out.println("Customer login is Unsuccessful - Test Failed\n");
	////			}
			String loginDashboardPageTitleText = sescuritycodepageobj.getDashboardPageTitle();
			String expectedText = "Dashboard: CareHubble";
			Assert.assertEquals(loginDashboardPageTitleText, expectedText);
			System.out.println("User login is Successful - Test Passed\n");
			logger.pass("Test passed - User Logged in Successfully");
		}
		
		@Test(priority = 5)
		public void headerDropdown() throws InterruptedException {
			logger = report.createTest("Open Header Menu");
			Header headerclassobj = new Header(browser);
			logger.info("Positive Test Case - Click on Header menu to open drop down");
			headerclassobj.headerDropdownArrow();
			System.out.println("Test passed - Dropdown opened Successfully\n");
			logger.pass("Test passed - Dropdown opened Successfully");
			
		}
		
		@Test(priority = 6)
		public void profilemenuoption() throws InterruptedException {
			logger = report.createTest("Select My Profile Menu Option");
			MyProfilePage myprofilepageobj = new MyProfilePage(browser);
			logger.info("Positive Test Case - Click to Select My Profile Menu Option from Header drop down");
			myprofilepageobj.headerDropdownMyProfileOption();
			System.out.println("Test passed - Navigated to My Profile View Successfully\n");
			logger.pass("Test passed - Navigated to My Profile View Successfully");
			
		}
		
		@Test(priority = 7)
		public void navigatetoeditprofilesteps() throws InterruptedException {
			logger = report.createTest("Navigate to Other Documents Steps");
		//	AboutMeStep aboutmeobj = new AboutMeStep(browser);
			browser.get("https://qa.carehubble.com/provider-profile/documents/3751643957893");
			logger.info("Positive Test Case - Enter URL OF Other Documents Steps");
		//	aboutmeobj.editProfileOption();
			System.out.println("Test passed - Navigated to Other Documents Successfully\n");
			logger.pass("Test passed - Navigated to Other Documents Successfully");
			
		}
		
		
		@Test(priority = 8)
		public void uploadimage() throws InterruptedException {
			
			logger = report.createTest("Uploading of Image in Background Check Section");
			OtherDocuments otherdocsobj = new OtherDocuments(browser);
			logger.info("Positive Test Case - Uploading of Image for Background Check field");
		    Thread.sleep(6000);
		    JavascriptExecutor jse = (JavascriptExecutor)browser;
		    jse.executeScript("window.scrollBy(0,250)", "");
			String imagePath = System.getProperty("user.dir") + "/Images\\atoll.jpg";
			otherdocsobj.backgroundCheckImage(imagePath);
			System.out.println("Test passed - Image uploaded Successfully\n");
			logger.pass("Test passed - Image uploaded Successfully");
			Thread.sleep(6000);
			
		}
		
//		@Test(priority = 9)
//		public void maleoptionofgender() throws InterruptedException {
//			logger = report.createTest("Selection of Gender Option");
//			AboutMeStep aboutmeobj = new AboutMeStep(browser);
//			logger.info("Positive Test Case - Selection of Gender from Drop down Options");
//			aboutmeobj.maleViaDropDown();
//			logger.pass("Test passed - Gender option Selected Successfully");
//			Thread.sleep(6000);
//			
//		}

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
