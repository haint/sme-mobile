package atm.demo.tests;

import junit.framework.Assert;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import io.selendroid.device.DeviceTargetPlatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SelendroidTest {
	private static SelendroidLauncher selendroidServer = null;
	  private static WebDriver driver = null;

	  @Test
	  public void testShouldBeAbleToEnterText() {
	    WebElement inputField = driver.findElement(By.id("my_text_field"));
	    inputField.sendKeys("Selendroid");
	    Assert.assertEquals("Selendroid", inputField.getText());
	  }

	  @BeforeClass
	  public static void startSelendroidServer() throws Exception {
	    if (selendroidServer != null) {
	      selendroidServer.stopSelendroid();
	    }
	    SelendroidConfiguration config = new SelendroidConfiguration();
	    config.addSupportedApp("src/main/resources/selendroid-test-app-0.10.0.apk");
	    selendroidServer = new SelendroidLauncher(config);
	    selendroidServer.lauchSelendroid();

	    SelendroidCapabilities caps =
	        new SelendroidCapabilities("io.selendroid.testapp:0.10.0");
	    caps.setPlatformVersion(DeviceTargetPlatform.ANDROID10);
	    caps.setEmulator(true);

	    driver = new SelendroidDriver(caps);
	  }

	  @AfterClass
	  public static void stopSelendroidServer() {
	    if (driver != null) {
	      driver.quit();
	    }
	    if (selendroidServer != null) {
	      selendroidServer.stopSelendroid();
	    }
	  }
}
