package com.cermati.assignment.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class scenario2 {
	
	WebDriver driver;

	@BeforeMethod
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test
	public void accessProductViaSearch()
	{
		String productSearch = "Macbook";
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(productSearch);
		
		Select select = new Select(driver.findElement(By.xpath("//select[@aria-label='Select a category for search']")));
		select.selectByVisibleText("Computers/Tablets & Networking");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//-----------Verify page loads completely------------------------
		
	      JavascriptExecutor j = (JavascriptExecutor) driver;
	      j.executeScript("return document.readyState")
	      .toString().equals("complete");
		
		//--------------------Verify First Result matches with Search String--------------------
	      
		String firstResultName = driver.findElement(By.xpath("(//div[@class='s-item__title']/span/span)[1]")).getText();
		System.out.println(firstResultName);
		Assert.assertTrue(firstResultName.contains(productSearch), "Not Matched.");
	}
}
