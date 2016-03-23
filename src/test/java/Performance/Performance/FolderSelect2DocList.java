package Performance.Performance;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;
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

public class FolderSelect2DocList {
	private WebDriver driver;
	private BrowserMobProxyServer server;
	public Long DocList_LoadUp_Time;
  @Test
  public void main() throws FindFailed {
	  
	  //// create a new HAR with the label "yahoo.com"
	  //server.newHar("Docman10");
		
	  SimpleDateFormat TimeFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
	//Create and initialize an instance of Screen object
	  Screen screen = new Screen();
	  
	  //Open Docman 10 search
	  driver.get("http://192.168.1.92/Docman10_Instance/Search/Search");
	  //driver.get("https://10.docmansandpit.com/Docman10_Instance/Search/Search");
	  String title = driver.getTitle();
	  System.out.println("Title is " + title);
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  
	  //Search for Carly Gill
	  driver.findElement(By.id("dm-search-for")).sendKeys("Carly Gill");
	  
	  //Click search button
	  driver.findElement(By.id("dm-search-submit")).click();
	  
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  
	  
	  
	  driver.findElement(By.xpath("//*[contains(text(), 'GILL, Carly (Mrs)')]")).click(); 
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  //Click the arrow button to display folder list
	  driver.findElement(By.id("dm-show-folders")).click();
	  
	  try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  //Choose the folder
	  WebElement folder2click = driver.findElement(By.xpath("//*[contains(text(), 'Paediatric Dentistry')]")); 
	  folder2click.click();
	  Date ClickTime = Calendar.getInstance().getTime();
	  System.out.println("Click folder time is " + ClickTime.getTime() );
	  
	  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	  
	  //Check how many document on the document list
	  int folder_Doc_Count =Integer.parseInt(driver.findElement(By.id("dm-document-list-folder-count")).getText());
	  int Doc_Count = driver.findElements(By.className("dm-document-li")).size();
	  
	  
	  System.out.println("Doc Count is " + Doc_Count);
	  System.out.println("Folder list " + folder_Doc_Count);
	  Assert.assertTrue((Doc_Count == folder_Doc_Count));
	  
	  Date DurationTime = Calendar.getInstance().getTime();
	  System.out.println("Duration time is " + DurationTime.getTime());

	 
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  
	  try {
		FileUtils.copyFile(scrFile, new File("C:\\Performance_Result\\performance_viewhistory_" + TimeFormat.format(Calendar.getInstance().getTime()) + ".png"));
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	  
	  
	  
	  DocList_LoadUp_Time = (DurationTime.getTime() - ClickTime.getTime());
	  System.out.println("The Document list load up time is " + DocList_LoadUp_Time + " milliseconds");
	  
	  /*
	  Har har = server.getHar();
		
		
	  //Write the HAR Data in a File
		
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
	  Setup setup = new Setup("Chrome");
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
