package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript extends FunctionLibrary{
String inputpath = "./FileInput/TestData_sheet.xlsx";
String outputpath = "./FileOutput/DataDriven_Results.xlsx";
ExtentReports reports;
ExtentTest logger;
@Test
public void starttest() throws Throwable
{
	reports = new ExtentReports("./Reports/login.html");
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc = xl.rowcount("Login");
	Reporter.log("no.of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		logger = reports.startTest("Login Test");
		logger.assignAuthor("Bharath");
		String username = xl.getcelldata("Login", i, 0);
		String password = xl.getcelldata("Login", i, 1);
		logger.log(LogStatus.INFO,username+"   "+password);
		boolean res = FunctionLibrary.adminlogin(username, password);
		if(res)
		{
			xl.setcelldata("Login", i, 2, "Login success", outputpath);
			xl.setcelldata("Login", i, 3, "pass", outputpath);
			logger.log(LogStatus.PASS, "valid credentials");
		}
		else
		{
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./screenshot/iteration/"+i+"Login.png"));
			xl.setcelldata("Login", i, 2, "Login failed", outputpath);
			xl.setcelldata("Login", i, 3, "fail", outputpath);
			logger.log(LogStatus.FAIL, "invalid credentials");
		}
		reports.endTest(logger);
		reports.flush();
	}
	
}
}
