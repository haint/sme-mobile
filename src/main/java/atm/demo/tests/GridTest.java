package atm.demo.tests;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GridTest {
	WebDriver driver = null;

	@Test
	public void test() throws InterruptedException {
		while (driver.findElements(By.className("android.widget.Button")).size() == 0) {
			System.out.println("Init....");
		}

		List<WebElement> buttons = driver.findElements(By.className("android.widget.Button"));
		WebElement name = buttons.get(0);
		name.click();
		name.sendKeys("sme-demo");

		System.out.println(driver.getPageSource());

		WebElement passwd = driver.findElements(By.className("android.widget.EditText")).get(1);
		passwd.click();
		passwd.sendKeys("QWer!@#$%");


		buttons = driver.findElements(By.className("android.widget.Button"));
		for (WebElement button : buttons) {
			if ("Sign in".equals(button.getText())) {
				button.click();
				break;
			}
		}

		System.out.println(driver.getPageSource());

		while (driver.findElements(By.className("android.widget.ProgressBar")).size() != 0) {
			System.out.println("Signing .....");
		}


		((AppiumDriver)driver).sendKeyEvent(82);
		Thread.sleep(1000);

		List<WebElement> texts = driver.findElements(By.className("android.widget.TextView"));
		texts.get(0).click();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("haithanh86");

		while (driver.findElements(By.className("android.widget.ProgressBar")).size() != 0) {
			System.out.println("Searching .....");
		}

		texts = driver.findElements(By.className("android.widget.TextView"));

		for (WebElement e : texts) {
			System.out.println(e.getText());
		}
		texts.get(1).click();

		Thread.sleep(3000);

		System.out.println(driver.getPageSource());

		WebElement msgTxt = driver.findElement(By.id("com.skype.raider:id/message_area_edit_text"));

		msgTxt.click();

		msgTxt.sendKeys("Hello my friend");

		driver.findElement(By.id("com.skype.raider:id/message_area_emoticon_button")).click();

		Thread.sleep(2000);

		List<WebElement> emotions = driver.findElements(By.className("android.widget.ImageView"));

		emotions.get(new Random().nextInt(emotions.size() - 1)).click();

		driver.findElement(By.id("com.skype.raider:id/message_area_send_button")).click();

		driver.navigate().back();

		driver.findElement(By.id("com.skype.raider:id/recents_button")).click();

	}

	/**
	 * Init webdriver
	 */
	@Parameters({ "browser", "version", "platform", "automationName", "api", "device", "appActivity" })
	@BeforeMethod
	public void setup(String browser, String version, String platform, @Optional("automationName") String automationName,
			@Optional("api") String api, String device, String appActivity) {
		try {
			File app = new File("com.skype.raider-1.apk");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
			capabilities.setCapability(CapabilityType.VERSION, version);
			capabilities.setCapability(CapabilityType.PLATFORM, platform);
			capabilities.setCapability("device", device);
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "com.skype.raider");
			capabilities.setCapability("appActivity", "Main");

			driver = new AppiumDriver(new URL("http://localhost:6666/wd/hub"),
					capabilities);
		} catch (Exception e) {
			System.out.println("Loi roi: Something wrong with Appium Server");
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
}
