package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


import io.github.bonigarcia.wdm.WebDriverManager;


public class BasePage {
	
	WebDriver driver;
	 Properties prop;
	 public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	 
	 /**
	  * This method is used to initialize the WebDriver on the basis of given browser name
	  * @param browserName
	  * @return driver
	  */
	 	public WebDriver init_driver(Properties prop) {
	 		String browserName=prop.getProperty("browser").trim();
	 		System.out.println("Browser Name is :" + browserName);

	 		if (browserName.equalsIgnoreCase("chrome")) {
	 			WebDriverManager.chromedriver().setup();
	 			if(Boolean.parseBoolean(prop.getProperty("remote"))){
	 				init_remoteWebDriver(browserName);
	 			}else{
	 				//driver = new ChromeDriver(optionsManager.getChromeOptions());
	 				tlDriver.set(new ChromeDriver());
	 			}
	 			
	 			
	 		} else if (browserName.equalsIgnoreCase("firefox")) {
	 			WebDriverManager.firefoxdriver().setup();
	 			
	 			if(Boolean.parseBoolean(prop.getProperty("remote"))){
	 				init_remoteWebDriver(browserName);
	 				
	 			}else{
	 				//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
	 				tlDriver.set(new FirefoxDriver());
	 			}
	 			
	 		}

	 		else if (browserName.equalsIgnoreCase("safari")) {
	 			//driver = new SafariDriver();
	 			tlDriver.set(new SafariDriver());
	 		} else {
	 			System.out.println("Please pass correct browser name :" + browserName);
	 		}
	 		getDriver().manage().deleteAllCookies();
	 		getDriver().manage().window().maximize();
	 		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 		getDriver().get(prop.getProperty("url"));
	 		
	 		return getDriver();
	 	}
	 	
	 	/**
	 	 * This method with define the desired capabilities and will initialize the driver with capabilities.
	 	 * Also this method with initialize the driver with Selenium Hub/port using docker
	 	 */
	 	private void init_remoteWebDriver(String browserName){
	 		
	 		if(browserName.equalsIgnoreCase("chrome")){
	 			DesiredCapabilities cap=DesiredCapabilities.chrome();
	 			cap.setCapability(ChromeOptions.CAPABILITY,new ChromeDriver());
	 			
	 			
	 			try {
	 				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
	 			} catch (MalformedURLException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	 		}
	 		else if(browserName.equalsIgnoreCase("firefox")){
	 			DesiredCapabilities cap= DesiredCapabilities.firefox();
	 			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS , new FirefoxDriver());

	 			try {
	 				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
	 			} catch (MalformedURLException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	 		}
	 		
	 	}
	 	
	 	
	 	/**
	 	 * getDriver using ThreadLocal
	 	 */
	 	public static synchronized WebDriver getDriver() {
	 		return tlDriver.get();
	 	}
	 	
	 	/**
	 	 * This method is used to get properties values from config file
	 	 * @return it returns prop
	 	 */
	 	
	 	public Properties init_prop(){
	 		prop= new Properties();
	 		String path=null;
	 		String env=null;
	 		
	 		
	 		try {
	 			env=System.getProperty("env");
	 			System.out.println("Running on environment:"+ env);
	 			
	 			if(env==null){
	 				path="/Users/nimps/eclipse-workspace/BeamAutomation/src/main/java/config/config.properties";
	 				System.out.println("Running on environment:"+ "PROD");
	 			}
	 			else{
	 				switch (env){
	 				case "qa":
	 					path="/Users/nimps/eclipse-workspace/BeamAutomation/src/main/java/config/config.qa.properties";
	 					break;
	 				case "dev":
	 					path="/Users/nimps/eclipse-workspace/BeamAutomation/src/main/java/config/config.dev.properties";
	 					break;
	 				default:
	 					System.out.println("Please pass the correct environment value");
	 					break;
	 				}
	 			}
	 			
	 			
	 			FileInputStream ip= new FileInputStream(path);
	 			prop.load(ip);
	 		} catch (FileNotFoundException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 		return prop;
	 	}
	 	
	 	/**
	 	 * This method is used to take screenshot
	 	 */
	 	public String getScreenshot() {
	 		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	 		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
	 		File destination = new File(path);
	 		try {
	 			FileUtils.copyFile(src, destination);
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		return path;
	 	}


}
