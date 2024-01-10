package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports  generateExtentReporter(){
	
	ExtentReports extentReport = new ExtentReports();
	
	//path where report should be created in the form of html file
	
	File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	
	//color theme
	ExtentSparkReporter sparkReporter  = new ExtentSparkReporter(extentReportFile);
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("TutorialsNinja Test Automation results");
	sparkReporter.config().setDocumentTitle("TN Automation test results report");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties congfigProp = new Properties();
	File conPropFile = new File (System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
	try {
		FileInputStream fisConfigProp = new FileInputStream(conPropFile);
		congfigProp.load(fisConfigProp);
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	extentReport.setSystemInfo("Application URL ", congfigProp.getProperty("url"));
	extentReport.setSystemInfo("Browser Name",congfigProp.getProperty("browserName"));
	extentReport.setSystemInfo("Email",congfigProp.getProperty("validEmail"));
	extentReport.setSystemInfo("Password",congfigProp.getProperty("validPassword"));
	extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
	extentReport.setSystemInfo("Username",System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
	return extentReport;
	
	
	}

}
