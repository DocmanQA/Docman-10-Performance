package Performance.Performance;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Setup {
	public static String title;
	public static String Browser;
	public static WebDriver driver;
	public static BrowserMobProxyServer server;
	public static DesiredCapabilities capabilities;
	
	public Setup()
	{
		System.out.println("Setup 1");
		Browser = "FireFox";
	}
	
	public Setup(String WebBrowser)
	{
		System.out.println("Setup 2");
		if (WebBrowser == "IE64")
		{
			System.setProperty("webdriver.ie.driver", "C:\\Selenium Doc\\IEDriverServer_64.exe");
			Browser = "IE64";  
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(caps);
		}
		if (WebBrowser == "IE32")
		{
			System.setProperty("webdriver.ie.driver", "C:\\Selenium Doc\\IEDriverServer.exe");
			Browser = "IE32";  
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(caps);
		}
		else if (WebBrowser == "Chrome")
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium Doc\\chromedriver.exe");
			Browser = "Chrome";
			driver = new ChromeDriver();
		}
		else if (WebBrowser == "Android")
		{
			Browser = "Android";
			
		}
		
	}
	
	public static BrowserMobProxyServer startProxy(){
		
		//start the proxy
		server = new BrowserMobProxyServer();
		server.start(0);
		
		// get the Selenium proxy object
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(server);
		
		//configure it as a desired capability
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		
		return server;
	}
	
	public static WebDriver startDriver() {
		
		// start the browser up
		if (Browser.equals("FireFox") == true){
			//driver = new FirefoxDriver(capabilities);
			driver.manage().window().maximize();
		}
		else if (Browser.equals("IE64") == true){
			//driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
		}
		else if (Browser.equals("IE32") == true){
			//driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
		}
		else if (Browser.equals("Chrome")== true){
			//driver = new ChromeDriver(capabilities);
			driver.manage().window().maximize();
		}
		else if (Browser.equals("Android") == true){
			DesiredCapabilities capabilities = new DesiredCapabilities();

			  // Set android deviceName desired capability. Set your device name.
			  //capabilities.setCapability("deviceName", "6cbbda77");
			  capabilities.setCapability("deviceName", "emulator-5554");

			  // Set BROWSER_NAME desired capability. It's Android in our case here.
			  //capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
			  capabilities.setCapability(CapabilityType.BROWSER_NAME, "Browser");

			  // Set android VERSION desired capability. Set your mobile device's OS version.
			  capabilities.setCapability(CapabilityType.VERSION, "5.1.1");

			  // Set android platformName desired capability. It's Android in our case here.
			  capabilities.setCapability("platformName", "Android");
			  //capabilities.setCapability("orientation", "LANDSCAPE");

			  //Additional Settings
			  capabilities.setCapability("disableAndroidWatchers", false);
			  capabilities.setCapability("deviceReadyTimeout", 30);
			  capabilities.setCapability("androidDeviceReadyTimeout", 30);
			  capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			  // Set android appPackage desired capability. It is
			  // com.android.calculator2 for calculator application.
			  // Set your application's appPackage if you are using any other app.
			  //capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
			  //capabilities.setCapability("appPackage", "com.android.calculator2");

			  // Set android appActivity desired capability. It is
			  // com.android.calculator2.Calculator for calculator application.
			  // Set your application's appPackage if you are using any other app.
			  //capabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
			  //capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

			  // Created object of RemoteWebDriver will all set capabilities.
			  // Set appium server address and port number in URL string.
			  // It will launch calculator app in android device.
			  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		
		
		
		return driver;
		
	}

}
