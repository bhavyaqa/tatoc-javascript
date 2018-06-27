package com.qainfotech.automation.tatoc_javascript;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest extends App
{
   @BeforeClass
   public void launch_browser() {
	   super.launchBrowser();   
   }
   @Test
   public void all_tatoc() {
	   super.openBasic();
	   super.gridGate();
	   super.frameDungeon();
	   super.dragAround();
	   super.popupWindow();
	   super.cookieHandling();
   }
   

   @AfterClass
   public void close_browser() {
	   driver.quit();
   }
}
