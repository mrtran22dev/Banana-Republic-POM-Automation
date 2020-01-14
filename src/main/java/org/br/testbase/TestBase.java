package org.br.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.br.excelUtil.ReadWriteExcelUtil;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	
	protected static WebDriver driver;
	protected static String url = "https://www.bananarepublic.com";
	static Dimension d = new Dimension(1200, 800);
  
  public static void init() {
	  //String browser = ReadWriteExcelUtil.getBrowser();
	  String browser = "chrome";												// default browser					
	  Properties prop = new Properties();
	  FileInputStream fis;
	  try {
		  fis = new FileInputStream("C:/Users/mt/eclipse-workspace1/auto-pom/resources/config.properties");
		  prop.load(fis);
		  browser = prop.getProperty("browser");
		  System.out.println("browser: " + browser);
	  } catch (FileNotFoundException e) {
		  System.out.println("config.properties file not found");
		  e.printStackTrace();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
			
	  if (browser.equals("firefox")) {
		  System.setProperty("webdriver.gecko.driver", "C:\\Users\\mt\\Downloads\\Selenium\\geckodriver-v0.22.0-win64\\geckodriver.exe");
		  System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:/Users/mt/eclipse-workspace1/auto-pom/logs.txt");
		  driver = new FirefoxDriver();
		  driver.manage().window().setSize(d);
	  } else if (browser.equals("chrome")) {
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\mt\\Downloads\\Selenium\\chromedriver_win32\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().window().setSize(d);
	  } else {
		  System.setProperty("webdriver.gecko.driver", "C:\\Users\\mt\\Downloads\\Selenium\\geckodriver-v0.22.0-win64\\geckodriver.exe");
		  System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:/Users/mt/eclipse-workspace1/auto-pom/logs.txt");
		  driver = new FirefoxDriver();
	  }
	  
	  driver.get(url);
  }
  
  public static void init3() {
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\mt\\Downloads\\Selenium\\geckodriver-v0.22.0-win64\\geckodriver.exe");
	  driver = new FirefoxDriver();
  }
  
}
