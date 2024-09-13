package applicationLayer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class SupplierPage {
WebDriver driver;
public SupplierPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath = "(//a[contains(text(),'Suppliers')])[2]")
WebElement clicksupplier;
@FindBy(xpath = "(//span[@data-phrase='AddLink'])[1]")
WebElement clickaddicon;
@FindBy(xpath = "//input[@id='x_Supplier_Number']")
WebElement suppliernumber;
@FindBy(xpath = "//input[@id='x_Supplier_Name']")
WebElement suppliername;
@FindBy(xpath = "//textarea[@id='x_Address']")
WebElement address;
@FindBy(xpath = "//input[@id='x_City']")
WebElement city;
@FindBy(xpath = "//input[@id='x_Country']")
WebElement country;
@FindBy(xpath = "//input[@id='x_Contact_Person']")
WebElement contactperson;
@FindBy(xpath = "//input[@id='x_Phone_Number']")
WebElement phonenumber;
@FindBy(xpath = "//input[@id='x__Email']")
WebElement email;
@FindBy(xpath = "//input[@id='x_Mobile_Number']")
WebElement mobilenumber;
@FindBy(xpath = "//textarea[@id='x_Notes']")
WebElement notes;
@FindBy(id = "btnAction")
WebElement clickaddbutton;
@FindBy(xpath = "//span[@data-caption='Search']")
WebElement searchpanel;
@FindBy(xpath = "//input[@id='psearch']")
WebElement searchtextbox;   
@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement searchbutton;  
@FindBy(xpath ="//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")
WebElement webtable;
@FindBy(xpath = "//button[normalize-space()='OK!']")
WebElement popupok1 ;
@FindBy(xpath = "//div[@class='alert alert-success ewSuccess']")
WebElement popupmsg;
@FindBy(xpath = "//button[@class='ajs-button btn btn-primary']")
WebElement popupok2 ;

public boolean addsupplier(String suppliername, String address , String city , String country , 
		String contactperson , String phonenumber , String email , String mobilenumber , String notes )
{
	clicksupplier.click();
	clickaddicon.click();
	String expected = suppliernumber.getAttribute("value");
	this.suppliername.sendKeys(suppliername);
	this.address.sendKeys(address);
	this.city.sendKeys(city);
	this.country.sendKeys(country);
	this.contactperson.sendKeys(contactperson);
	this.phonenumber.sendKeys(phonenumber);
	this.email.sendKeys(email);
	this.mobilenumber.sendKeys(mobilenumber);
	this.notes.sendKeys(notes);
	clickaddbutton.sendKeys(Keys.ENTER);
	popupok1.click();
	String alrtmsg = popupmsg.getText();
	Reporter.log(alrtmsg,true);
	popupok2.click();
	if(!searchtextbox.isDisplayed())
		searchpanel.click();
	searchtextbox.clear();
	searchtextbox.sendKeys(expected);
	searchbutton.click();
	String actual = webtable.getText();
	if(actual.equalsIgnoreCase(expected))
	{
		Reporter.log("addsupplier is success::::"+expected+"   "+actual);
		return true;
	}
	else
	{
		Reporter.log("addsupplier is failed::::"+expected+"   "+actual);
		return false;
	}
	
}
}
