package com.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.pages.SearchPage;
import com.pages.WelcomePage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	public BasePage basePage;
	public WelcomePage welcomePage;
	public SearchPage searchPage;

	@BeforeTest
	public void setUp(){
		basePage=new BasePage();
		prop=basePage.init_prop();
	    driver=basePage.init_driver(prop);
	    welcomePage=new WelcomePage(driver);
    	}
	//Uncomment this when you run the test cases from testng_regression.xml passing @Parameter annotations
//	@Parameters({"browser"})
//	@BeforeTest
//	public void setUp(String browserName){
//		basePage=new BasePage();
//		prop=basePage.init_prop();
//		prop.setProperty("browser", browserName);
//		driver=basePage.init_driver(prop);
//		loginPage=new LoginPage(driver);
//	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
