package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountScessPage {
	 WebDriver driver;
	 
	@FindBy(xpath ="//div[@id='content']/h1")
	private WebElement accountSuccesspageHeading; 
	
	
	
	public AccountScessPage ( WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	public String retrieveAccountSyccessPageHeading() {
		String AccountSuccessPageHeadingText = accountSuccesspageHeading.getText() ;
		return AccountSuccessPageHeadingText;
		
	}
	
	

}
