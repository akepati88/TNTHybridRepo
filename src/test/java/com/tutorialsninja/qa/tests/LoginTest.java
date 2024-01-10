package com.tutorialsninja.qa.tests;


import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;



public class LoginTest extends Base{

	public WebDriver driver ; //ChromeDriver();
	LoginPage loginpage ;

	public LoginTest() {
		super(); //calling base class constructor with properties file or we can create a method with properties file configurations 

	}

	@BeforeMethod
	public void setup() throws InterruptedException {


		driver = initalizeBrowserAndAppURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		Thread.sleep(3000);
		loginpage  =homepage.navigateToLoginPage();
	}



	@Test(priority=1,dataProvider="validCredentailsSupplier")	//to this method we are feeding data via data provider 
	public void verifyLoginWithValidCreds(String email, String Password) throws InterruptedException {

		
	
		//Thread.sleep(3000);
		
		AccountPage accountPage= 	loginpage.login(email,Password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information is not displayed assertion failed" );

	}
	/* hardcoded
	@DataProvider
	public Object[][] supplyTestData() {
		Object[][] data  = {{"amotooricap@gmail.com", "12345"},
				{"amotooricap1@gmail.com", "12345"},{"amotooricap3@gmail.com", "12345"}};
		return data;
	}

	 */
	//reading from excel file
	@DataProvider(name="validCredentailsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data  = Utilities.getTestDataFromExcel("Login");
		return data;
	}





	@Test(priority=2)
	public void verifyloginWithInvalidCreds() throws InterruptedException {
		
		
		loginpage.login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
		Thread.sleep(3000);
		
		String Actualerrortxt = loginpage.retriveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage= dataProp.getProperty("emailPswdNomatching");
		Assert.assertTrue(Actualerrortxt.contains(expectedWarningMessage),"Expectedwarning message is not displayed");


	}
	@Test(priority=3)
	public void verifyloginWithValidPasswordInvalidEmail() throws InterruptedException {
		
		loginpage.login(Utilities.generateEmailWithTimeStamp(),prop.getProperty("validPassword"));
		String Actualerrortxt = loginpage.retriveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage= dataProp.getProperty("emailPswdNomatching");
		Assert.assertTrue(Actualerrortxt.contains(expectedWarningMessage),"Expectedwarning message is not displayed");


	}

	@Test(priority=4)
	public void verifyloginWithVlaiddEmailInvalidPswd() throws InterruptedException {
		
		
		loginpage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
		Thread.sleep(3000);
		String Actualerrortxt = loginpage.retriveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage= dataProp.getProperty("emailPswdNomatching");
		Assert.assertTrue(Actualerrortxt.contains(expectedWarningMessage),"Expectedwarning message is not displayed");


	}
	@Test(priority=5)
	public void verifyloginWithoutCreds() throws InterruptedException {
		
	
		loginpage.clickOnLoginButton();

		String Actualerrortxt = loginpage.retriveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage= dataProp.getProperty("emailPswdNomatching");
		Assert.assertTrue(Actualerrortxt.contains(expectedWarningMessage),"Expectedwarning message is not displayed");

	}


	public String generateTimeStamp() {
		// Method cahining
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");


	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
