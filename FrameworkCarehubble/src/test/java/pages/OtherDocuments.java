package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OtherDocuments extends BaseClass{
	
	private By backgroundcheckLocator = By.cssSelector("input#file");
	private By savebuttonLocator = By.cssSelector("button.btn.btn-primary");
	//private By basicinfoLocator = By.cssSelector("span.d-flex.align-items-center");
	//private By genderdropdownLocator = By.cssSelector("div:contains(“Select Pronouns”)");
	//private By maleoptionLocator = By.cssSelector("input[value='female']");
	
	public OtherDocuments(WebDriver browserDriver) {
		super(browserDriver);
		// wait for page to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(savebuttonLocator));
	}
	
	public void backgroundCheckImage(String imagePath) {
		wait.until(ExpectedConditions.elementToBeClickable(savebuttonLocator));
		WebElement backgroundcheckfield = browser.findElement(backgroundcheckLocator);
		backgroundcheckfield.sendKeys(imagePath);
}
	
//	public void basicInfoMenu() {
//		wait.until(ExpectedConditions.elementToBeClickable(basicinfoLocator));
//		WebElement editbutton = browser.findElement(basicinfoLocator);
//		editbutton.click();
//}
	
}