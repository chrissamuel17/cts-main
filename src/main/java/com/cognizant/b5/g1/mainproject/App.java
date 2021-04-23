package com.cognizant.b5.g1.mainproject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class App 
{
	private static WebDriver driver;
	private static Properties objPro = new Properties();
	
	@BeforeSuite
	public void setupProperties() {

		try {
			FileInputStream objFis = new FileInputStream(".\\src\\prop.properties");
			objPro.load(objFis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Data Provider
	@DataProvider
	public void readExcel() {
		//For excel
	}
	
	//To setup the driver
	@BeforeTest
	public void setupDriver(){
		
		objPro.getProperty("browser");
		String browserName = objPro.getProperty("browser");

		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();

			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();

			driver.manage().window().maximize();
		} else {
			System.out.println("browser is not correct");
			System.exit(0);
		}
		
		driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
		
	}
	
	//To close Browser
	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}
	
    public static void main( String[] args )
    {
        App a = new App();
        a.setupDriver();
        a.closeBrowser();
    }
}
