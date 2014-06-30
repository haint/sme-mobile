package atm.demo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IFindElement {
	String cBrowser;
	WebDriver driver;
	
	public IFindElement(WebDriver webDriver, String browser) {
		cBrowser=browser;
		driver = webDriver;
	}
	
	public WebElement findByTextCustom(String controlType, String textValue) {
		if (cBrowser.equals("Selendroid")) {
			return driver.findElement(By.linkText(textValue));
		}

		// Sample: //Button/[@text='value']
		return driver.findElement(By.xpath("//" + controlType + "[@text='" + textValue + "']"));
	}
}
