package com.qainfotech.automation.tatoc_javascript;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class App 
{
	WebDriver driver;
	JavascriptExecutor js;
	public App() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\bhavyakhanna\\Downloads\\chromedriver_win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.js = (JavascriptExecutor) this.driver;
	}
	
	public void launchBrowser() {
		
		driver.get("http://10.0.1.86/tatoc");
	}

	public void openBasic() {
		js.executeScript("document.getElementsByTagName('a')[0].click()");
	}

	public void gridGate() {
		js.executeScript("document.getElementsByClassName('greenbox')[0].click()");
		
	}

	public void frameDungeon() {
		WebElement mainframe = (WebElement)js.executeScript("return document.getElementById('main')");
		driver.switchTo().frame(mainframe);
		WebElement box1 = (WebElement) js.executeScript("return document.getElementById('answer')");
		String color1 = box1.getAttribute("class");
		WebElement childframe = (WebElement) js.executeScript("return document.getElementById('child')");
		driver.switchTo().frame(childframe);
		WebElement box2 = (WebElement) js.executeScript("return document.getElementById('answer')");
		String color2 = box2.getAttribute("class");
		while (!color1.equals(color2)) {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(mainframe);
			js.executeScript("document.getElementsByTagName('a')[0].click()");
			driver.switchTo().frame(childframe);
			box2 = (WebElement) js.executeScript("return document.getElementById('answer')");;
			color2 = box2.getAttribute("class");
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainframe);
		List<WebElement> anchors = 	(List<WebElement>) js.executeScript("return document.getElementsByTagName('a')");

		anchors.get(anchors.size() - 1).click();
	}

	public void dragAround() {
		
		WebElement from = (WebElement) js.executeScript("return document.getElementById('dragbox')");
		WebElement to = (WebElement) js.executeScript("return document.getElementById('dropbox')");
		Actions act = new Actions(driver);
		act.dragAndDrop(from, to).build().perform();
		js.executeScript("document.getElementsByTagName('a')[0].click()");
	}

	public void popupWindow() {
		List<WebElement> popup = (List<WebElement>) js.executeScript("return document.getElementsByTagName('a')");
		popup.get(0).click();
		String originalwindow = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);

		}
		js.executeScript("document.getElementById('name').setAttribute('value','Bhavya')");
		js.executeScript("document.getElementById('submit').click()");
		driver.switchTo().window(originalwindow);
		popup.get(1).click();
	}

	public void cookieHandling() {
		List<WebElement> CookieHandlingLinks = (List<WebElement>) js.executeScript("return document.getElementsByTagName('a')");
		CookieHandlingLinks.get(0).click();
		String token = (String)js.executeScript("return document.getElementById('token').innerText");
		String newtoken = token.replace("Token: ", "");
		Cookie tatoc_cookie = new Cookie("Token", newtoken);
		driver.manage().addCookie(tatoc_cookie);
		CookieHandlingLinks.get(1).click();
	}
}
