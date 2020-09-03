package com.in28minutes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumHubTest {
	//selenium-standalone start -- -role hub

	//Nodes should register to http://192.168.8.69:4444/grid/register/
	
	//Clients should connect to http://192.168.8.69:4444/wd/hub
	
	//selenium-standalone start -- -role node -hub http://192.168.8.69:4444/grid/register/ 
	
	//selenium-standalone start -- -role node -port 5556 -hub http://192.168.8.69:4444/grid/register/ 

	@Test
	public void hub_chrome() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capabilites = new DesiredCapabilities();
		
		//chrome, firefox, htmlunit, internet explorer, iphone, opera
		capabilites.setBrowserName("chrome");
		//capabilites.setPlatform(Platform.EL_CAPITAN);
		
		//WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver();
		WebDriver remoteDriver = new RemoteWebDriver(
				new URL("http://51.138.40.183:8100/wd/hub"), capabilites);
		remoteDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//RemoteWebDriver
		//	Location of Standaloneserver
		//  Which Browser? Which OS? => Capabilities
		
		remoteDriver.get("http://40.114.181.69/debjyoti/app");
		System.out.println(remoteDriver.getCurrentUrl());
		System.out.println(remoteDriver.getTitle());
		String pageTitle = remoteDriver.getTitle();
		assertEquals("Using Dockerizing Jenkins Pipeline", pageTitle);
		Thread.sleep(10000);
		String result = remoteDriver.findElement(By.xpath("/html/body/p")).getText();
		System.out.println("the paragraph string is " + result);
		assertEquals("Hello from Kubernetes. Testing selenium integration - commit 1", result);

		remoteDriver.quit();
	}

	
}
