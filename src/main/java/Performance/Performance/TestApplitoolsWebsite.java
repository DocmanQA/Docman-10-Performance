package Performance.Performance;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.RectangleSize;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URISyntaxException;

public class TestApplitoolsWebsite {
	
	public static void applitool() throws URISyntaxException, InterruptedException {
		
		WebDriver driver = new FirefoxDriver();
		
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

}
