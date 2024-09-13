package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
@Test
public static boolean adminlogin(String user,String pass) throws Throwable
{
	driver.get(conpro.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
	driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(user);
	driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
	driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
	String expected = "dashboard";
	String actual = driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("login is success::"+expected+"   "+actual,true);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
		return true;
	}
	else
	{
		String errormsg = driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
		Thread.sleep(3000);
		driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
		Reporter.log(errormsg,true);
		return false;
	}
}
}
