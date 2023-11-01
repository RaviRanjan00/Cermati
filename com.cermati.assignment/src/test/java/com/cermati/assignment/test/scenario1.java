package com.cermati.assignment.test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class scenario1 {
	
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
	public void multipleFilter()
	{
		driver.findElement(By.xpath("//button[@id='gh-shop-a']")).click();
		driver.findElement(By.xpath("//a[text()='Cell phones & accessories']")).click();
		driver.findElement(By.xpath("//a[text()='Cell Phones & Smartphones']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("//button[@aria-label='All Filters']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.findElement(By.xpath("//div/span[text()='Condition']")).click();
        driver.findElement(By.xpath("//label/span[text()='New']")).click();
        
        driver.findElement(By.xpath("//div/span[text()='Price']")).click();
        driver.findElement(By.xpath("//input[contains(@aria-label,'Minimum Value')]")).sendKeys("100");
        driver.findElement(By.xpath("//input[contains(@aria-label,'Maximum Value')]")).sendKeys("1000");

        driver.findElement(By.xpath("//div/span[text()='Item Location']")).click();
        driver.findElement(By.xpath("//input[@value='US Only']")).click();
        
        driver.findElement(By.xpath("//button[@aria-label='Apply']")).click();
        
        //-----------Assertion for All Filter Condition-------------------------
        
        String filterCondition = driver.findElement(By.xpath("(//span[@class='brm__flyout__btn-label'])[1]")).getText();
        Assert.assertEquals(filterCondition, "3 filters applied");
	}
}
