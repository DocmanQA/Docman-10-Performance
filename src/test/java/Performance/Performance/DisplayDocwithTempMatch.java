package Performance.Performance;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

import Performance.Performance.*;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class DisplayDocwithTempMatch {
	private WebDriver driver;
	public String Browser;
	private BrowserMobProxyServer server;
	public Long Doc_LoadUp_Time;
	private Pattern image;
	public String ScreenName;
	public double xx = 0.0;
	public double yy = 0.0;
	public double ImageMatch = 0;
	public boolean ImMatch = false;
	
  @Test
  
  public void main() throws FindFailed {
	  
	  //// create a new HAR with the label "yahoo.com"
	  //server.newHar("Docman10");
		
	  SimpleDateFormat TimeFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
	//Create and initialize an instance of Screen object
	  Screen screen = new Screen();
	  if (Setup.Browser.equals("Android")){
		 //Add image path  
		  Pattern image_AVD = new Pattern("C:\\Performance_Result\\CompareImage\\AVD.png");
		  System.out.println(screen.exists("C:\\Performance_Result\\CompareImage\\AVD_HL.png", 5));
		  if (screen.exists("C:\\Performance_Result\\CompareImage\\AVD_HL.png", 5) == null){
		  
			  screen.click(image_AVD, 200);
		  }
	   }
	  
	  //Open Docman 10 search
	  //driver.get("http://192.168.1.92/Docman10_Instance/Search/Search");
	  driver.get("https://10.docmansandpit.com/Docman10_Instance/Search/Search");
	  String title = driver.getTitle();
	  System.out.println("Title is " + title);
	  
	  //Search for Carly Gill
	  driver.findElement(By.id("dm-search-for")).sendKeys("Carly Gill");
	  
	  //Click search button
	  driver.findElement(By.id("dm-search-submit")).click();
	  
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  Date OpenBrowserTime = Calendar.getInstance().getTime();
	  System.out.println("Open time is " + OpenBrowserTime.getTime() );
	  
	  driver.findElement(By.xpath("//*[contains(text(), 'GILL, Carly (Mrs)')]")).click(); 
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  //Click the first document on the document list
	  driver.findElements(By.id("dm-document-a")).get(0).click();
	  //driver.findElement(By.xpath("//*[contains(text(), 'Clinical letter re urethral washings')]")).click(); 
	  //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  do{
		  try {
			  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  ScreenName = "C:\\Performance_Result\\performance_DisDoc_" + TimeFormat.format(Calendar.getInstance().getTime()) + ".png";
			  FileUtils.copyFile(scrFile, new File(ScreenName));
			  System.out.println(ScreenName);

		  } catch (IOException e2) {
				// TODO Auto-generated catch block
					  e2.printStackTrace();
				  }

		  double match[] = new TempMatch().run(ScreenName, "C:\\Performance_Result\\CompareImage\\Doc_1.png", "C:\\Performance_Result\\CompareImage\\Result.png", Imgproc.TM_CCOEFF);
	      xx = match[0];
	      yy = match[1];
	      ImageMatch = match[2];
	      if (ImageMatch > 9.0E8)
	      {
	    	  ImMatch = true;
	      }
		  System.out.println("x is " + xx + " y is " + yy);
		  System.out.println("Match is " + ImageMatch + ", ImMatch is " + ImMatch);
		  
	  } while (ImMatch != true);
	  
	  System.out.println("After match is " + ImageMatch);
	  
	  Date DurationTime = Calendar.getInstance().getTime();
	  System.out.println("Close time is " + DurationTime.getTime());
	  Doc_LoadUp_Time = (DurationTime.getTime() - OpenBrowserTime.getTime());
	  System.out.println("The Load up time is " + Doc_LoadUp_Time + " milliseconds");
	  
	  
	  //Har har = server.getHar();
		
		
	  //Write the HAR Data in a File
		
	  //File harFile = new File("C:\\Performance_Result\\performance_DisDoc_" + TimeFormat.format(Calendar.getInstance().getTime()) + ".har");
		
	  //try {
		//		har.writeTo(harFile);
		//	} catch (IOException e) {
		// TODO Auto-generated catch block
		//	e.printStackTrace();
		//	}
  }
  @BeforeTest
  public void beforeTest() {
	  Setup setup = new Setup("IE32");
	  //server = setup.startProxy();
	  driver = setup.startDriver();
	  System.load("C:/OpenCV2/opencv/build/java/x64/opencv_java2412.dll");
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
