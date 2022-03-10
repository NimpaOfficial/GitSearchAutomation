package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.BasePage;
import com.util.ConstantsUtil;
import com.util.ElementUtil;

public class SearchPage extends BasePage{
	private WebDriver driver;
	ElementUtil elementUtil;
	
	//By Locator-OR
	private By header=By.tagName("h3");
	private By repoUrl=By.xpath("//a[text()='NimpaOfficial/']");
	private By repository=By.xpath("(//div//p)[1]");
	
	public SearchPage(WebDriver driver){
		elementUtil=new ElementUtil(driver);
		this.driver=driver;
	}
	
	public String searchPageTitle(){
		return elementUtil.waitForTitlePresent(ConstantsUtil.SEARCH_PAGE_TITLE, 10);
	}
	
	public String getHeaderValue(){
		if(elementUtil.doIsDisplayed(header)){
			return elementUtil.doGetText(header);
		}
		return null;
	}
	
	public String clickOnRepo(){
		
		elementUtil.waitForElementPresent(repoUrl, 10);
		elementUtil.doClick(repoUrl);
		if(elementUtil.doIsDisplayed(repository)){
			return elementUtil.doGetText(repository);
		}
		return null;
	}
	
	

}
