package First.Testselenium;

import static org.testng.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageAction.CsdnLogin;
import utils.DriverFactory;


/**
 * @author YR
 *
 */
public class CsdnLoginTest {
	
	private WebDriver driver;
	
	@Before
	public void setUp(){
		System.out.println("start.....");
		driver =DriverFactory.getChromDriver();
		System.out.println("this.....?");
		driver.get("https://passport.csdn.net/");
	}
	@Test
	public void testCsdnLogin() throws InterruptedException{
		CsdnLogin csdnpage = new CsdnLogin(driver);
//		Thread.sleep(8000);
		csdnpage.setName("1234556");
//		Thread.sleep(8000);
		csdnpage.setPassWord("123456");
//		Thread.sleep(8000);
		csdnpage.clickButton();
//		Thread.sleep(8000);
//		assertEquals("帐户名或登录密码不正确，请重新输入", csdnpage.getErrorMessage());
		if("https://passport.csdn.net/".equals(driver.getCurrentUrl())){
			assertEquals("帐户名或登录密码不正确，请重新输入", csdnpage.getErrorMessage());
			System.out.println("清空输入框，进入下一个测试数据的运行");
			csdnpage.clearTextBox();
		}else{
			assertEquals("zaq1zse4", csdnpage.getSucessName());
			System.out.println("跳转到其他页面，重新进入该页面，运行下面的测试数据");
			driver.get("https://passport.csdn.net/");			
		}
	}
	@After
	public void tearDown(){
		driver.quit();
	}
}
