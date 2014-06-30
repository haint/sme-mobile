package atm.demo.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.ObjectFactory;

public class DataGeneratorUtil {
	@Deprecated
	public static WebDriver setupWebDriver() throws MalformedURLException {
		WebDriver driver;
		
		File appDir = new File("D://Testing");
		File app = new File(appDir, "CW.Sherlock.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", "Android");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.VERSION, "4.2.2");
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("app-package", "com.huy.cw.sherlock");
		capabilities.setCapability("app-activity", "MainWithActionBar");
		// capabilities.setCapability("app-wait-activity","LoginActivity,NewAccountActivity");

		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		//driver = new SwipeableWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		return driver;
	}
	
	public static String getRandomColour(){
		Random random = new Random();
		String[] arrColours = {"Red","Yellow","Black","Grey"};
		return arrColours[random.nextInt(arrColours.length)];
	}

	public static String getRandomAddress(){
		Random random = new Random();
		String[] arrData = {"36 Xuan Thuy, Cau Giay","16 Duy Tan, Cau Giay","68 Pham Hung","CA, US","Q1, Ho Chi Minh"};
		return arrData[random.nextInt(arrData.length)];
	}
	
	public static String getRandomAge(){
		Random random = new Random();
		return String.valueOf(random.nextInt(80));
	}
	
	public static String getRandomHeight(){
		Random random = new Random();
		return String.valueOf(random.nextInt(180));
	}

}
