package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class AccountRegistrationTest extends BaseClass{

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		
		logger.info("***starting AccountRegistrationTest***");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link");
		
		hp.clickRegister();
		logger.info("clicked on register");
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		logger.info("providing customer details");
		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString().toUpperCase()+"@gmail.com");
		regpage.setTelephone(randomAlphaNumber().toUpperCase());
		
		String password= randomAlphaNumber();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setchkdPolicy();
		regpage.clickContinue();
		
		logger.info("validating expected method");
		String confmsg= regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test failed...");
			logger.debug("debug logs...");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****finished account registration test****");
		}
}
