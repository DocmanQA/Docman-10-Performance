package Performance.Performance;

import java.net.URISyntaxException;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.RectangleSize;

import Performance.Performance.Setup;
import Performance.Performance.TestApplitoolsWebsite;


public class PerformanceTest {
  @Test
  public void main() throws URISyntaxException, InterruptedException {
	  //Setup setup = new Setup("IE64");
	  //setup.start();
	  
	  //TestApplitoolsWebsite applitools = new TestApplitoolsWebsite();
	  //applitools.applitool();
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium Doc\\chromedriver.exe");
	  //WebDriver driver = new FirefoxDriver();
	  WebDriver driver = new ChromeDriver();
		
		Eyes eyes = new Eyes();
		//This is my api key
		eyes.setApiKey("j7SaVTjbI1QGZHmAxCO69S3JrWw7vCpeNoqDF14KHK0110");
		
		try{
			driver = eyes.open(driver, "Applitools", "Test Web Page", new RectangleSize(1024, 768));
		
			driver.get("http://applitools.com");
		
			// Visual validation point 
			eyes.checkWindow("Main Page");
			driver.findElement(By.cssSelector(".features>a")).click();
		
			//visual validation point 
			eyes.checkWindow("Features page");
		
			//end visual testing. validate visual correctness.
			eyes.close();
		} finally{
			eyes.abortIfNotClosed();
			driver.close();
		}
	  
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
