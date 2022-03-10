package com.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.util.ConstantsUtil;

public class SearchPageTest extends BaseTest{
	
	@BeforeClass
	public void searchPageSetup(){
	searchPage=welcomePage.doSearch("HybridFrameWorkImplementation");
	
	}
	
	@Test(priority=1)
	public void verifySearchPageTitleTest(){
		String title=searchPage.searchPageTitle();
		System.out.println("The title of the Home Page:"+title);
		Assert.assertEquals(title,ConstantsUtil.SEARCH_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifySearchPageHeaderTest(){
		String header =searchPage.getHeaderValue();
		System.out.println("The Header of HomePage is:"+ header);
		Assert.assertEquals(header.contains("1 repository result"), ConstantsUtil.SEARCH_PAGE_HEADER_TRIM);
	}
	
	@Test(priority=3)
	public void navigateToRepo(){
	String repositoryName=searchPage.clickOnRepo();
	if(repositoryName.contains("HubSpot POM Hybrid Framework")) {
		Assert.assertTrue(true);
	}
	else
		Assert.fail("Wrong Repository");
	
	}

}
