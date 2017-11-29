package First.Testselenium;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.Select;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class testexample {

//	public static void main(String[] args) {
//		System.out.println("start ChromeDriver browers");
//		System.setProperty("webdriver.chrome.driver", "webdriver\\chromedriver.exe");		
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://www.baidu.com");
//		List<WebElement> links = driver.findElements(By.cssSelector("#u1 a"));
//		//验证链接数量
//		assertEquals(9, links.size());
//		//打印href属性
//		for (int i = 0; i < links.size(); i++) {
//		System.out.println(links.get(i).getAttribute("href"));
//		}		
//		driver.close();       
//	}
	
	@Test
	public void test(){
		
//		testRemoteWebDriver();
		testWindowsUtils();
		
		System.out.println("start ChromeDriver browers");
		System.setProperty("webdriver.chrome.driver", "webdriver\\chromedriver.exe");	
//		ChromeOptions options = new ChromeOptions();
//		System.out.println("进入到谷歌页面");
//		options.addArguments("user-data-dir=C:/Users/PC/AppData/Local/Google/Chrome/User Data");
//		options.addArguments("--start-maximized");
//		System.out.println("初始化driver");
		WebDriver driver = new ChromeDriver();
//		System.out.println("打开百度页面");
		driver.get("http://www.baidu.com");
		
//		testTakesScreenShot(driver);
//		testJavaScriptCalls(driver);
//		testFindElement(driver);
//		testWindowMaxsize(driver);
//		testDropDown(driver);
//		testWithImplicitWait(driver);
//		testWebDriverWait(driver);
//		testAlter(driver);
		testFrameset(driver);
		
		driver.close();

	}
	
	public void testFindElement(WebDriver driver) {
		
		driver.findElement(By.id("kw")).sendKeys("百度一下");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			 e.printStackTrace();
		}
		driver.findElement(By.id("kw")).clear();
		driver.findElement(By.name("wd")).sendKeys("测试人员");
	    assertTrue("abcd".contains("a"));
	    
	}
	
	public void testJavaScriptCalls(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String title = (String)js.executeScript("return document.title");
	    long links = (Long)js.executeScript("var links =" + "document.getElementsByTagName('A');" + "return links.length");
	    System.out.println("title is :" + title + "  " + "链接数量共计：" + links);
	}
	
	public void testTakesScreenShot(WebDriver driver) {
		try{
		File scr_file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr_file, new File("E:\\code\\Testselenium\\screenshot\\screenshot2.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testRemoteWebDriver() {
		System.out.println("1111111");
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		WebDriver driver = null;
		try {
			System.out.println("222222");
			driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"), capability);
			System.out.println("333333");
		} catch ( MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("444444");
		driver.get("http://www.baidu.com");
		
		driver = new Augmenter().augment(driver);
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("E:\\code\\Testselenium\\screenshot\\screenshot3.png"));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void testWindowMaxsize(WebDriver driver) {
		driver.manage().window().maximize();
	}
    public void testDropDown(WebDriver driver) {
    	driver.get("file:///E:/select.html");
    	WebElement see = driver.findElement(By.cssSelector("select[name=carlist]"));
    	org.openqa.selenium.support.ui.Select test = new org.openqa.selenium.support.ui.Select(see);
//    	assertTrue(test.isMultiple());
    	assertEquals(4, test.getOptions().size());
    	System.out.println(test.getFirstSelectedOption().getText());
    	test.selectByVisibleText("Audi");
    	System.out.println(test.getFirstSelectedOption().getText());
    	assertEquals("Audi", test.getFirstSelectedOption().getText());
    	test.selectByValue("volvo");
    	test.selectByIndex(3);	
    }
    
    public void testWindowsUtils(){
    	WindowsUtils.tryToKillByName("chromedriver.exe");
    }
    
    public void testWithImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		WebElement baidu = driver.findElement(By.id("kw"));
		baidu.sendKeys("测试");
		driver.findElement(By.id("su1")).click();	
	}
    
    public void testWebDriverWait(WebDriver driver) {
    	WebElement baidu = driver.findElement(By.id("kw"));
		baidu.sendKeys("测试");
		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("su1")));
		WebElement massage = wait.until(new ExpectedCondition<WebElement>(){
			                            public WebElement apply(WebDriver d) {
											return d.findElement(By.id("su1"));
										}
		});
		
		massage.click();
		
	}
    
    private boolean isElementPresent(WebDriver driver,By by) {
    	try {
    	driver.findElement(by);
    	return true;
    	} catch (Exception e) {
    	e.printStackTrace();
    	return false;
    	}
    	}
    
    public void testAlter(WebDriver driver) {
		driver.get("file:///E:/alter.html");
		boolean isselect = driver.findElement(By.id("male")).isSelected();
		System.out.println(isselect);
		
		driver.findElement(By.cssSelector("input[type=button]")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			 e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
    
    public void testFrameset(WebDriver driver) {
		driver.get("file:///E:/frameset.html");
		driver.switchTo().frame("left");
//		String leftmsg = driver.findElement(By.tagName("p")).getText();
		driver.switchTo().frame(0);
		System.out.println(driver.findElement(By.tagName("p")).getText());
		driver.switchTo().defaultContent();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("right");
		System.out.println(driver.findElement(By.tagName("p")).getText());
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		System.out.println(driver.findElement(By.tagName("p")).getText());
		
		
		driver.switchTo().defaultContent();
//		driver.switchTo().frame("left");
		List<WebElement> frames = driver.findElements(By.tagName("frame"));
		System.out.println(frames.size());
		
	}
    
    
}
