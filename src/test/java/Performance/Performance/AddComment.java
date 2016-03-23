package Performance.Performance;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class AddComment {
	private WebDriver driver;
	public Long AddComment_Time;
	public boolean folder;
  @Test
  public void main() throws FindFailed {
	  
	  //// create a new HAR with the label "yahoo.com"
	  //server.newHar("Docman10");
		
	  SimpleDateFormat TimeFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
	 //Create and initialize an instance of Screen object
	  Screen screen = new Screen();
	  
	  //Open Docman 10 search
	  driver.get("http://192.168.1.92/Docman10_Instance/Search/Search");
	  //driver.get("http://10.docmansandpit.com/Docman10_Instance/Search/Search");
	  String title = driver.getTitle();
	  System.out.println("Title is " + title);
	  
	  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	  
	  //key in carly gill
	  driver.findElement(By.id("dm-search-for")).sendKeys("carly gill");
	  //click the submit button
	  driver.findElement(By.id("dm-search-submit")).click();
	  
	  WebDriverWait wait = new WebDriverWait(driver, 5);
 
	  WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/Docman10_Instance/DocumentViewer/Documents?SectionId=2&ItemId=14']")));
	  driver.findElement(By.cssSelector("a[href*='/Docman10_Instance/DocumentViewer/Documents?SectionId=2&ItemId=14']")).click();
	  
	  WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("dm-document-a")));
	  
	  driver.findElements(By.id("dm-document-a")).get(2).click();
	 
	  WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("dm-action-comments")));
	  
	  driver.findElement(By.id("dm-action-comments")).click();
	  
	  driver.findElements(By.id("dm-check")).get(0).click();
	  
	  driver.findElement(By.id("dm-comment-add-input")).click();
	  Date clickTime = Calendar.getInstance().getTime();
	  
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  //driver.findElement(By.cssSelector("div[class='ajs-message ajs-success ajs-visible']"));
	  driver.findElement(By.xpath("//*[contains(text(), 'Comment(s) Added')]"));
	  Date PopupTime = Calendar.getInstance().getTime();
	  
	 
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  
	  try {
		FileUtils.copyFile(scrFile, new File("C:\\Performance_Result\\performance_AddComment_" + TimeFormat.format(Calendar.getInstance().getTime()) + ".png"));
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	  
	  AddComment_Time = PopupTime.getTime() - clickTime.getTime();
	  
	  System.out.println("Change Folder Time is " + AddComment_Time + " milliseconds");
	  
	 
  }
  @BeforeTest
  public void beforeTest() {
	  Setup setup = new Setup("Chrome");
	  driver = setup.startDriver();
  }

  @AfterTest
  public void afterTest() {		
	//close the browser
	driver.quit();
  }
  
  public void beforelooptest(String Brow){
	  Setup setup = new Setup(Brow);
	  driver = setup.startDriver();
  }

}
