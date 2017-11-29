package utils;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author YR
 *
 */

public class DriverFactory {
	private static String chromedriver;
	private static String IEDriverserver;
	private static Properties p = null;
	private static String config = System.getProperty("user.dir")+System.getProperty("file.separator")+"src\\main\\resources\\config.properties";
	private static Log log = new Log(DriverFactory.class);
	
	public static WebDriver getChromDriver(){
		try {
			p = new ConfigUtils().getProperties(config);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		if(p != null){
			chromedriver = p.getProperty("chromedriver");						
		}
		log.info("chrome driver path is " + chromedriver);
		System.setProperty("webdriver.chrome.driver", chromedriver);
		WebDriver driver = new ChromeDriver();		
		return driver;
	}
	
	public static WebDriver getIEDriver(){
		try {
			p = new ConfigUtils().getProperties(config);
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		if(p != null){
			IEDriverserver = p.getProperty("IEDriverServer");
		}
		log.info("IEDriverserver");
		System.setProperty("webdriver.ie.driver", IEDriverserver);
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		WebDriver driver = new InternetExplorerDriver(caps);
		return driver;
	}
	
	
}
