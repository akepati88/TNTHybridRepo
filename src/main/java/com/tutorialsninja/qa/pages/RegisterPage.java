package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy (id="input-firstname")
	private WebElement  firstNameFeiled;
	
	@FindBy (id="input-lastname")
	private WebElement lasttNameFeiled;
	
	@FindBy (id="input-email")
	private WebElement  emailAddressFeild;
	
	@FindBy(id="input-telephone")
	private WebElement telePhoneFeild;
	
	@FindBy(id="input-password")
	private WebElement passwordFeild;
	
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmFeild;
	
	@FindBy(name="agree")
	private WebElement PrivacyPolicyFeild;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	
	
	
	
	
	public RegisterPage (WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public AccountScessPage registerWithAMandatoryFeilds(String firstNametext ,String lastName,String emailText,String telePhoneText,String PassordText,String PassordConfirmText) {
		firstNameFeiled.sendKeys(firstNametext);
		lasttNameFeiled.sendKeys(lastName);
		emailAddressFeild.sendKeys(emailText);
		telePhoneFeild.sendKeys(telePhoneText);
		passwordFeild.sendKeys(PassordText);
		passwordConfirmFeild.sendKeys(PassordConfirmText);
		PrivacyPolicyFeild.click();
		continueButton.click();
		return new AccountScessPage(driver);
	}
	
	
	public AccountScessPage registerWithAllFeilds(String firstNametext ,String lastName,String emailText,String telePhoneText,String PassordText,String PassordConfirmText) {
		firstNameFeiled.sendKeys(firstNametext);
		lasttNameFeiled.sendKeys(lastName);
		emailAddressFeild.sendKeys(emailText);
		telePhoneFeild.sendKeys(telePhoneText);
		passwordFeild.sendKeys(PassordText);
		passwordConfirmFeild.sendKeys(PassordConfirmText);
		yesNewsLetterButton.click();
		PrivacyPolicyFeild.click();
		continueButton.click();
		return new AccountScessPage(driver);
	}
	
	public void enterFirstName(String firstNametext  ) {
		firstNameFeiled.sendKeys(firstNametext);
		
	}
	
	public void enterLastName(String lastName) {
		lasttNameFeiled.sendKeys(lastName);
		
	}
	
	public void eaterEmailAddress(String emailText) {
		emailAddressFeild.sendKeys(emailText);
		
	}
	
	public void enterTelephoneNumber(String telePhoneText) {
		telePhoneFeild.sendKeys(telePhoneText);
		
	}
	
	public void enterPassword(String PassordText) {
		passwordFeild.sendKeys(PassordText);
		
	}
	
	public void enterConfirmPassword(String PassordConfirmText) {
		passwordConfirmFeild.sendKeys(PassordConfirmText);
		
	}
	
	public void selectPrivacyPolicy() {
		PrivacyPolicyFeild.click();
		
	}
	
	public AccountScessPage selectContinue() {
		continueButton.click();
		return new AccountScessPage(driver);
		
		
	}
	
	public void selectYesNewsletterOption() {
		yesNewsLetterButton.click();
	}
	

	public String retrieveDuplicateEmailAddressWarning() {

		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}

	public String retrievePrivacyPolicyWarning() {

		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;

	}

	public String retrieveEmailWarning() {

		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}


	public String retrieveFirstNameWarning() {

		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}


	public String retrieveLastNameWarning() {

		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}


	public String retrieveTelephoneWarning() {

		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}

	public String retrievePasswordWarning() {

		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
