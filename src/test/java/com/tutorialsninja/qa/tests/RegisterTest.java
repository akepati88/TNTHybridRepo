package com.tutorialsninja.qa.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountScessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	public WebDriver driver ; 
	RegisterPage registerPage;
	AccountScessPage AccountScessPage ;


	public RegisterTest() {

		super();
	} 


	@BeforeMethod
	public void setup() {
		driver = initalizeBrowserAndAppURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		registerPage= homepage.navigateToRegisnPage();

	}


	@Test(priority=1)
	public void VerifyRegisterAnaccountWithMandatoryFeilds() {

		AccountScessPage= registerPage.registerWithAMandatoryFeilds(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				(Utilities.generateEmailWithTimeStamp()), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));

		String actualSuccessHeading = AccountScessPage.retrieveAccountSyccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account is not created");



	}

	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {


		AccountScessPage= registerPage.registerWithAllFeilds(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				(Utilities.generateEmailWithTimeStamp()), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));

	
		String actualSuccessHeading = AccountScessPage.retrieveAccountSyccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account is not created");



	}

	//@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {

		AccountScessPage= registerPage.registerWithAllFeilds(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
		prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));




		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		//Assert.assertTrue(actualWarning,dataProp.getProperty("duplicateEmailWarning"),"Warning message regaring duplicate email address is not displayed");


	}

	//@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		registerPage.selectContinue();

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}


}


