package Performance.Performance;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Performance.Performance.*;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class ViewHistory {
	private WebDriver driver;
	private BrowserMobProxyServer server;
	public Long History_LoadUp_Time;
  @Test
  public void main() throws FindFailed {
	  
	  //// create a new HAR with the label "yahoo.com"
	  //server.newHar("Docman10");
		
	  SimpleDateFormat TimeFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
	//Create and initialize an instance of Screen object
	  Screen screen = new Screen();
	  
	  //Open Docman 10 search
	  //driver.get("https://10.docmansandpit.com/Docman10_Instance/Search/Search");
	  driver.get("http://192.168.1.92/Docman10_Instance/Search/Search");
	  String title = driver.getTitle();
	  System.out.println("Title is " + title);
	  
	  //Search for Carly Gill
	  driver.findElement(By.id("dm-search-for")).sendKeys("Carly Gill");
	  
	  //Click search button
	  driver.findElement(By.id("dm-search-submit")).click();
	  
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  
	  
	  driver.findElement(By.xpath("//*[contains(text(), 'GILL, Carly (Mrs)')]")).click(); 
	  try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  //Click the first document on the document list
	  driver.findElements(By.id("dm-document-a")).get(0).click();
	  //driver.findElement(By.xpath("//*[contains(text(), 'Clinical letter re urethral washings')]")).click(); 
	  
	  //Wait for the document rendered
	  Pattern image_1 = new Pattern("C:\\Performance_Result\\CompareImage\\Doc_1.png");
	  screen.wait(image_1, 3000);
     
	  //Click the view history button
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  WebElement history_button = driver.findElement(By.id("dm-action-history"));
	  history_button.click();
	  
	  Date ClickTime = Calendar.getInstance().getTime();
	  System.out.println("Click View History time is " + ClickTime.getTime() );

	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  //driver.findElement(By.xpath("//*[contains(text(), 'Latest')]"));
	  driver.findElement(By.className("dm-tl-panel"));
	  
	  //Add image path
	  Pattern image_2 = new Pattern("C:\\Performance_Result\\CompareImage\\Doc_2.png");
	  
	  //Wait for image
	  screen.wait(image_2, 3000);
	  //screen.hover(image_2);
	  
	  Date DurationTime = Calendar.getInstance().getTime();
	  System.out.println("Duration time is " + DurationTime.getTime());

	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  
	  try {
		FileUtils.copyFile(scrFile, new File("C:\\Performance_Result\\performance_viewhistory_" + TimeFormat.format(Calendar.getInstance().getTime()) + ".png"));
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	  
	  
	  History_LoadUp_Time = (DurationTime.getTime() - ClickTime.getTime());
	  System.out.println("The Load up time is " + History_LoadUp_Time + " milliseconds");
	  
	  
	  //Har har = server.getHar();
		
		
	  //Write the HAR Data in a File
		/*
	  File harFile = new File("C:\\Performance_Result\\performance_viewhistory_" + TimeFormat.format(Calendar.getInstance().getTime()) + ".har");
		
	  try {
				har.writeTo(harFile);
			} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			}
			*/
  }
  @BeforeTest
  public void beforeTest() {
	  Setup setup = new Setup("IE32");
	  //server = setup.startProxy();
	  driver = setup.startDriver();
  }

  @AfterTest
  public void afterTest() {
	//stop the BrowserMob Proxy Server
	//server.stop();
			
	//close the browser
	driver.quit();
  }
  
  public void beforelooptest(String Brow){
	  Setup setup = new Setup(Brow);
	  driver = setup.startDriver();
  }
}
