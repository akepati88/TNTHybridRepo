package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	
	@Override
	public void onStart(ITestContext context) {
		//System.out.println("execution of project tests started");
		 extentReport =  ExtentReporter.generateExtentReporter();
	}


	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println(testName+ "Started executing");
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName() +" Started executing");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//String testName = result.getName();
		extentTest.log(Status.PASS,result.getName()+"got successfully executed");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
        WebDriver driver = null;
		
        //getting driver
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver,result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got failed");
		
		System.out.println(result.getName()+ " test got failed");
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"  test name got skipped");
		
	}

	
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		System.out.println("completed executed project tests ");
		
		//below code helps to open test execution reports automatically on browser desktop
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
