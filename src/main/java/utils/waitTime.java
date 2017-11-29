package utils;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function; // fluenwait中的function需要导入的包



/**
 * @author YR
 *
 */

public class waitTime {
	//the first wait time method
	public void theFirstWaitMethod() throws InterruptedException{
		Thread.sleep(1000);
	}
	// the second wait time method
	public void theImplicitWait(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				
	}
	//the third wait time method
	public void theExplicitWait(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("jjjj"))));
		wait.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver driver){
				return driver.findElement(By.className("classname")).isDisplayed();
			}
		});
		
		WebElement message = new WebDriverWait(driver, 8).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
						try {
							return d.findElement(By.id("page4"));
						} catch (Exception e) {
							return null;
						}
					}
				});
		new WebDriverWait(driver, 10).until(new
				ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
				return
				d.findElement(By.id("userName")).getAttribute("readonly").contains
				("true");
				}});
		
		new WebDriverWait(driver, 10).until(new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
					return d.findElement(By.id("page4")).isDisplayed();
					}});
		
		new WebDriverWait(driver, 10).until(new
				ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
				JavascriptExecutor js = (JavascriptExecutor) d;
				return (Boolean)js.executeScript("return jQuery.active == 0");
				}});		

	}
	
	//
	public void testFluenWait(WebDriver driver){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement message = wait
				.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.id("page4"));
				}
				});	
		
	}
}
