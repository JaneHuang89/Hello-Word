package First.Testselenium;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;


import org.testng.annotations.Test;

import pageAction.CsdnLogin;
import utils.DriverFactory;


/**
 * @author YR
 *
 */
public class DataTestTng {
		
		private WebDriver driver;
		
		@BeforeTest
		public void setUp(){
			driver =DriverFactory.getChromDriver();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.get("https://passport.csdn.net/");
		}
		
		 //造测试数据	
		@DataProvider
		public Object[][] testData(){
			return new Object[][]{
					new Object[]{"123456","333333","帐户名或登录密码不正确，请重新输入"},
					new Object[]{"","",""},
					new Object[]{"zaq1zse4","zaq1zse4","zaq1zse4"},
					new Object[]{"33333","33333","333333"},
			};
		}
	
		@org.testng.annotations.Test(dataProvider = "testData")
		public void testCsdnLogin(String name,String pword,String expert) throws InterruptedException{
			CsdnLogin csdnpage = new CsdnLogin(driver);
//			Thread.sleep(8000);
			csdnpage.setName(name);
//			Thread.sleep(8000);
			csdnpage.setPassWord(pword);
//			Thread.sleep(8000);
			csdnpage.clickButton();
//			Thread.sleep(8000);
//			assertEquals(csdnpage.getErrorMessage(), expert);
			if(driver.getCurrentUrl().contains("/account/login")){
				assertEquals(csdnpage.getSucessName(), expert);
				driver.get("https://passport.csdn.net/");
			}else{
				assertEquals(csdnpage.getErrorMessage(), expert);
				csdnpage.clearTextBox();				
			}
		}

		@Test
		public void testTheOther() {
			System.out.println("会执行几次呢，嘻嘻");			
		}
		
		@AfterTest
		public void tearDown(){
			driver.quit();
		}
}
