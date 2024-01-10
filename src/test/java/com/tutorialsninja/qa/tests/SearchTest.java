package com.tutorialsninja.qa.tests;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{
	
	public WebDriver driver ;
	HomePage homepage;
	SearchPage serachPage;
	public SearchTest() {

		super();
	} 


	
	@BeforeMethod
	public void setup() {
		driver = initalizeBrowserAndAppURL(prop.getProperty("browser"));
		homepage = new HomePage(driver);
	}

	@Test(priority=1)
	public void verifySearchWithValidProduct() throws InterruptedException {

		serachPage = homepage.searchForAProduct(dataProp.getProperty("vlaidProduct"));
		Thread.sleep(5000);
		Assert.assertTrue(serachPage.displayStatusOfHPValidProduct(),"assertion failed due to valid hp product is displayed");

	}

	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		serachPage = homepage.searchForAProduct(dataProp.getProperty("invlaidProduct"));

		String ActualSearchmessage = serachPage.retriveNoProductMessageText();
		Assert.assertEquals(ActualSearchmessage, dataProp.getProperty("NoProductTextInSearchResults"), "verifySearchWithInvalidProductassertion falied");

	}

	//Test dependency annotation
	@Test(priority=3,dependsOnMethods={"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithOutAnyPrtoduct() {

		serachPage= homepage.clickOnSearchButton();

		String ActualSearchmessage = serachPage.retriveNoProductMessageText();
		Assert.assertEquals(ActualSearchmessage, dataProp.getProperty("NoProductTextInSearchResults"), "verifySearchWithInvalidProductassertion falied");

	}




	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

	


