package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("***Starting TC003_LoginDDT***");
		
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
		
		lgnpage.setEmail(email);
		lgnpage.setPassword(pwd);
		lgnpage.clickLogin();
		
		//myaccountpage
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.DoesMyAccountPageExists();
		
		/* Data is valid- login success- test pass - logout
        				login failed - test fail

		Data is invalid- login success- test fail - logout
          				login failed - test pass
		 */
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				logger.info("clicked on logout");
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				logger.info("clicked on logout");
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);;
			}
		}
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***Finished TC003_LoginDDT***");
		
	}
}


