package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement objreset;
	@FindBy(xpath = "//input[@id='username']")
	WebElement objuser;
	@FindBy(xpath = "//input[@id='password']")
	WebElement objpass;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objlogin;
	//write method for login
	public void adminlogin(String user,String pass) {
		objreset.click();
		objuser.sendKeys(user);
		objpass.sendKeys(pass);
		objlogin.click();
	}
}
