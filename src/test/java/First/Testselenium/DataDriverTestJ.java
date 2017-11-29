package First.Testselenium;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import pageAction.CsdnLogin;
import utils.DriverFactory;

/**
 * @author YR
 *
 */
@RunWith(Parameterized.class)
public class DataDriverTestJ {
	private WebDriver driver;
	private String name;
	private String pword;
	private String expert;
	
	@Before
	public void setUp(){
		driver =DriverFactory.getChromDriver();
		driver.get("https://passport.csdn.net/");
	}
	
	 //造测试数据	
	@Parameters
	public static List<String[]> data(){
		return Arrays.asList(new String[][]{
				{"123456","333333","帐户名或登录密码不正确，请重新输入"},
				{"","",""},
				{"zaq1zse4","zaq1zse4","zaq1zse4"},
				{"33333","33333","333333"}
		});
	}	
	public DataDriverTestJ(String name,String pword,String expert ){
		this.name = name;
		this.pword = pword;
		this.expert = expert;
	}
	
	@Test
	public void testCsdnLogin() throws InterruptedException{
		CsdnLogin csdnpage = new CsdnLogin(driver);
//		Thread.sleep(8000);
		csdnpage.setName(name);
//		Thread.sleep(8000);
		csdnpage.setPassWord(pword);
//		Thread.sleep(8000);
		csdnpage.clickButton();
//		Thread.sleep(8000);
//		assertEquals(csdnpage.getErrorMessage(), expert);
		if("https://passport.csdn.net/".equals(driver.getCurrentUrl())){
			assertEquals(csdnpage.getErrorMessage(), expert);
			csdnpage.clearTextBox();
		}else{
			assertEquals(expert, csdnpage.getSucessName());
			driver.get("https://passport.csdn.net/");			
		}
	}

	@After
	public void tearDown(){
		driver.quit();
	}
	
	
}
