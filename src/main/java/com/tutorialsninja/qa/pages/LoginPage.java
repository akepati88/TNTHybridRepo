package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement emailAddressFeild;
	
	@FindBy(id="input-password")
	private WebElement PasswordFeild;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMachingWarning;
	
	
	
	public LoginPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public AccountPage login(String emailtext ,String passwordText ) {
		emailAddressFeild.sendKeys(emailtext);
		PasswordFeild.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
		
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	
	
	public String retriveEmailPasswordNotMatchingWarningText() {
		String warningText = emailPasswordNotMachingWarning.getText();
		return warningText ;
		
		
		
		
	}
	
	
	

}
