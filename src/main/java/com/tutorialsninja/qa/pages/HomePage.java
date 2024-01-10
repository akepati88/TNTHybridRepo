package com.tutorialsninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenue;
	
	@FindBy(linkText= "Login")
	private WebElement LoginOption;
	
	@FindBy(linkText="Register")
    private WebElement registerOption;
	
	@FindBy(name="search")
    private WebElement serachBoxFeild;
	
	@FindBy(xpath="//div[@id ='search']/descendant::button")
    private WebElement serachButton;
	
	

	
	//this constructor will be called when we create an object for homepage
	public  HomePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public LoginPage navigateToLoginPage() {
		myAccountDropMenue.click();
		   LoginOption.click();
		   return new LoginPage(driver);
	}
	
	public RegisterPage navigateToRegisnPage() {
		myAccountDropMenue.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
	
   
   
   public RegisterPage SelectRegisterOption() {
	   registerOption.click();
	   return new RegisterPage(driver);
	   
   }
   
   public SearchPage searchForAProduct(String productText) {
	   serachBoxFeild.sendKeys(productText);
	   serachButton.click();
	   return new SearchPage(driver);
   }
  
   
   public SearchPage clickOnSearchButton() {
	   serachButton.click();
	   return new SearchPage(driver);
   }
}
