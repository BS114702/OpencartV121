package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity", "Master"})
	public void verify_Login()
	{
		logger.info("***Starting TC002_LoginTest***");
		
		try
		{
		//homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link");
		
		hp.clickLogin();
		logger.info("clicked on login");
		
		//login page
		LoginPage lgnpage= new LoginPage(driver);
		logger.info("providing user details");
		
		lgnpage.setEmail(p.getProperty("email"));
		lgnpage.setPassword(p.getProperty("password"));
		lgnpage.clickLogin();
		
		//myaccountpage
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.DoesMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true,"login failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***Finished TC002_LoginTest***");
		
	}

}
