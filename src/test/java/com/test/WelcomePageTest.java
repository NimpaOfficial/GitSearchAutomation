package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.util.ConstantsUtil;

public class WelcomePageTest extends BaseTest{
	
	@Test(priority=1)
	public void verifyHomePageTitle() {
		String title=welcomePage.getWelcomePageTitle();	
		System.out.println("LoginPage title is :"+title);
		Assert.assertEquals(title, ConstantsUtil.WELCOME_PAGE_TITLE);
	
	}
	
	public void searchRepository() {
		welcomePage.doSearch("HybridFrameWorkImplementation");
	}

}
