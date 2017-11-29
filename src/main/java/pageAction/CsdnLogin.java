package pageAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author YR
 *
 */

public class CsdnLogin {
	@FindBy(xpath = "//*[@id='username']")
	private WebElement username;
	@FindBy(xpath = "//*[@id='password']")
	private WebElement password;
	@FindBy(xpath = "//*[@id='fm1']/input[6]")
	private WebElement loginbutton;
	@FindBy(xpath = "//*[@id='error-message']")
	private WebElement errormessage;
	@FindBy(xpath = "/html/body/div[3]/div/div[3]/span")
	private WebElement loginsucess;
	
	private WebDriver driver;
	
	public CsdnLogin(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setName(String name){
		System.out.println(username.getAttribute("placeholder"));
		username.sendKeys(name);
	}
	
	public void setPassWord(String pword){
		System.out.println(password.getAttribute("placeholder"));
		password.sendKeys(pword);
	}
	
	public void clickButton(){
		loginbutton.click();
	}
	
	public String getErrorMessage(){
		return errormessage.getText();
	}
	
	public String getSucessName(){
		return loginsucess.getText();
	}
	
	public void clearTextBox(){
		username.clear();
		password.clear();
	}
	
}
