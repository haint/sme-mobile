package atm.demo.tests;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import atm.demo.utils.DataGeneratorUtil;

public class SingleTest {
	AppiumDriver driver = null;
	WebDriverWait wait;
	final int totalPeopleToAdd = 1;

	@Test
	public void testAddNewPerson() {
		List<WebElement> listControls;

		listControls = driver.findElementsByClassName("android.widget.Button");
		assert (listControls.size() > 0) : "No control was found";
		listControls.get(1).click();

		listControls = driver.findElements(By
				.className("android.widget.EditText"));
		System.out.println("Total EditText found:" + listControls.size());
		listControls.get(0).sendKeys(
				"Person " + DataGeneratorUtil.getRandomAge());
		listControls.get(1).sendKeys(DataGeneratorUtil.getRandomHeight());
		listControls.get(2).sendKeys(DataGeneratorUtil.getRandomAge());
		listControls.get(3).sendKeys(DataGeneratorUtil.getRandomColour());
		listControls.get(4).sendKeys("Cool");

		listControls = driver.findElements(By
				.className("android.widget.Button"));
		listControls.get(0).click();

		// Confirm
		listControls = driver.findElements(By
				.className("android.widget.Button"));
		listControls.get(1).click();

		// Back to list people
		listControls = driver.findElements(By
				.className("android.widget.Button"));
		listControls.get(1).click();
	}

	@Test
	public void sampleTest() {
		List<WebElement> listControls = driver
				.findElementsByClassName("android.widget.Button");
		assert (listControls.size() > 0) : "No control was found";
	}

	/**
	 * Init webdriver
	 */
	@BeforeMethod
	public void setup() {
		try {
			File app = new File("src/main/resources/selendroid-test-app-0.10.0.apk");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", "Android");
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
			capabilities.setCapability(CapabilityType.VERSION, "4.3");
			capabilities.setCapability(CapabilityType.PLATFORM, "LINUX");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "com.huy.cw.sherlock");
			capabilities.setCapability("appActivity", "MainWithActionBar");
			// capabilities.setCapability("app-wait-activity","LoginActivity,NewAccountActivity");

			driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"),
					capabilities);
		} catch (Exception e) {
			System.out.println("Loi roi: Something wrong with Appium Server");
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
