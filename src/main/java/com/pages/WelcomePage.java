package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.BasePage;
import com.util.ConstantsUtil;
import com.util.ElementUtil;


public class WelcomePage extends BasePage {
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	//By locators - OR 
		private By searchBtn= By.xpath("//input[@name='q']");
		
		
		//Constructor of the Page
		public WelcomePage(WebDriver driver){
			elementUtil=new ElementUtil(driver);
			this.driver=driver;
		}
		
		//Page Actions
		public String getWelcomePageTitle(){
			//return driver.getTitle();
			return elementUtil.waitForTitlePresent(ConstantsUtil.WELCOME_PAGE_TITLE, 20);
		}
		
		
		public SearchPage doSearch(String searchKey){
			elementUtil.doSendKeys(searchBtn, searchKey);
			elementUtil.pressKey(searchBtn);
			return new SearchPage(driver);
		}

	}


