package com.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	WebDriver driver;
	
	public ElementUtil(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * This is used to create WebElement on the basis of given By locator
	 * @param locator
	 * @return webelement
	 */
				public  WebElement  getElement(By locator){
					WebElement element=driver.findElement(locator);
					return element;
	}
				
				public  void doSendKeys(By locator,String value){
					getElement(locator).sendKeys(value);
					
				}
				
				public  void doClick(By locator){
					getElement(locator).click();
					
				}
				public void doActionSendKeys(By locator,String value){
					Actions act= new Actions(driver);
					act.sendKeys(getElement(locator), value).perform();
					act.sendKeys(getElement(locator), value).perform();
				}
				
				public void doActionClick(By locator){
					Actions act= new Actions(driver);
					act.click(getElement(locator)).perform();
				}
				
				public  String doGetText(By locator){
					 return getElement(locator).getText();
					
				}
				
				public  boolean doIsDisplayed(By locator){
					return  getElement(locator).isDisplayed();
					
				}
				public  void doLinkClick(List<WebElement> linksList,String value){
					System.out.println("Language List Size"+linksList.size());
					for(WebElement ele:linksList){
						String text=ele.getText();
						System.out.println(text);
						
						if(text.equals(value)){
							ele.click();
							break;
						}
						
					}
				}
				
				//*********************************Drop Down Util****************************
				
				public  void selectDropdownByVisibleText(By locator,String vtext){
					Select sel=new Select(getElement(locator));
					sel.selectByVisibleText(vtext);
				}
				
				public  void selectByIndex(By locator,int index){
					Select sel=new Select(getElement(locator));
					sel.selectByIndex(index);
				}
				
				public  void selectByValue(By locator,String value){
					Select sel=new Select(getElement(locator));
					sel.selectByValue(value);
				}
				
				public  int getDropDownOptionsCount(By locator){
					Select sel=new Select(getElement(locator));
					List<WebElement> optionsList=sel.getOptions();
					return optionsList.size();
				}
				
				public void pressKey(By locator) {
					getElement(locator).sendKeys(Keys.ENTER);
				}
				
				public  List<String> getDropDownOptionsValue(By locator){
					List<String> textList= new ArrayList<String>();
					
					Select sel=new Select(getElement(locator));
					List<WebElement> optionsList=sel.getOptions();
					
					for(WebElement ele:optionsList){
						String text=ele.getText();
						textList.add(text);
						}
						return textList;
					}
				
				public  void selectValueFromDropDownWithoutSelect(By locator, String value){
					List<WebElement> countryList=driver.findElements(locator);
							
							for(WebElement ele:countryList){
								String text=ele.getText();
								
								System.out.println(text);
								
								if(text.equals(value)){
									ele.click();
									break;
								}
							}
							
						}
				//*****************************Wait Utils************************************//
				
				public  List<WebElement> visibilityOfAllElements(By locator,int timeOut){
					WebDriverWait wait=new WebDriverWait(driver,timeOut);
					return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
					
				}
				
				public  void getPageLinksText(By locator,int timeOut){
					visibilityOfAllElements(locator,10).stream().forEach(ele ->System.out.println(ele.getText()));
					
				}
				public  WebElement waitForElementWithFluentWait(By locator,int timeOut, int interval){
					Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
							.withTimeout(Duration.ofSeconds(timeOut))
							.pollingEvery(Duration.ofSeconds(interval))
							.ignoring(NoSuchElementException.class,NullPointerException.class);
					return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				}

				public  WebElement waitForElementPresent(By locator,int timeOut){
					WebDriverWait wait= new WebDriverWait(driver,timeOut);
					return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
					
				}
				
				public  String waitForTitlePresent(String titleValue,int timeOut){
					WebDriverWait wait= new WebDriverWait(driver,timeOut);
					 wait.until(ExpectedConditions.titleIs(titleValue));
					return driver.getTitle();
				}
				
				public  WebElement waitForElementToBeVisible(By locator,int timeOut){
					WebElement element=getElement(locator);
					WebDriverWait wait= new WebDriverWait(driver,timeOut);
					return wait.until(ExpectedConditions.visibilityOf(element));
					
				}
				
				public Boolean waitForURL(String url,int timeOut){
					WebDriverWait wait=new WebDriverWait(driver,timeOut);
					return wait.until(ExpectedConditions.urlContains(url));
					
				}

				public Alert waitForAlertToBePresent(int timeOut){
					WebDriverWait wait=new WebDriverWait(driver,timeOut);
					return wait.until(ExpectedConditions.alertIsPresent());
					
				}
				public  WebElement waitForElementToBeClickable(By locator,int timeOut){
					WebDriverWait wait=new WebDriverWait(driver,timeOut);
					return wait.until(ExpectedConditions.elementToBeClickable(locator));
				}
				
				public  void clickWhenReady(By locator,int timeOut){
					WebDriverWait wait=new WebDriverWait(driver,timeOut);
					WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
					element.click();
				}



}

