package com.tutorialsninja.qa.tests;
import java.io.File;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class brainyquoteTest {
	
	
	
	WebDriver driver ;
	XSSFWorkbook workbook;
	
  @Test
	public void brainyquoteMethod() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://www.brainyquote.com/authors");
		List<WebElement> authorNames = driver.findElements(By.xpath("//div[@class='bq_fl indexContent authorContent']//div/a"));
		
		
		
		// Initialize Excel workbook and sheet
        workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Authors");
        int rowNum = 1;
        for (WebElement author : authorNames) {
            String AuthorName = author.getText();
            String Authortag = author.getTagName(); // Replace with the class name for the tags
            
            if(AuthorName.contains("Albert Einstein")) {
            	System.out.println(AuthorName);
            // Create a new row
            XSSFRow row = sheet.createRow(rowNum++);
            int cellNum = 0;
            
            // Set values in the cells
            row.createCell(cellNum++).setCellValue(AuthorName);
            row.createCell(cellNum).setCellValue(Authortag);
            
            // Write the data to an Excel file
            
            	File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\brainyquoteWebData.xlsx");
            
                FileOutputStream outputStream;
				try {
					outputStream = new FileOutputStream(file);
					workbook.write(outputStream);
	                //workbook.close();
	                //outputStream.close();
				} catch (Throwable e) {
	
					e.printStackTrace();
				} 
                
             
            
            }
        }
        
        System.out.println("Quotes written successfully to Excel file.");
		driver.close();
		
	}

}
