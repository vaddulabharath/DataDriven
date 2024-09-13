package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.SupplierPage;
import config.AppUtil1;
import utilities.ExcelFileUtil;

public class TestScript extends AppUtil1{
String inputpath = "./FileInput/supplierdata.xlsx";
String outputpath = "./FileOutput/SupplierResults.xlsx";
ExtentReports reports;
ExtentTest logger;
String Tcsheet = "supplier";
@Test
public void starttest() throws Throwable
{
	reports = new ExtentReports("./Extentreports/supplierreports.html");
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc = xl.rowcount(Tcsheet);
	Reporter.log("no.of rows are::"+rc);
	for(int i=1;i<=rc;i++)
	{
		logger = reports.startTest("SupplierTest");
				logger.assignAuthor("Bharath");
		String sname = xl.getcelldata(Tcsheet, i, 0);
		String address = xl.getcelldata(Tcsheet, i, 1);
		String city = xl.getcelldata(Tcsheet, i, 2);
		String country = xl.getcelldata(Tcsheet, i, 3);
		String cperson = xl.getcelldata(Tcsheet, i, 4);
		String pnumber = xl.getcelldata(Tcsheet, i, 5);
		String email = xl.getcelldata(Tcsheet, i, 6);
		String mnumber = xl.getcelldata(Tcsheet, i, 7);
		String notes = xl.getcelldata(Tcsheet, i, 8);
		logger.log(LogStatus.INFO, sname+"     "+address+"     "+city+"   "+country+"   "+
		                                  cperson+"   "+pnumber+"   "+email+"   "+mnumber+"   "+notes);
		SupplierPage sup = PageFactory.initElements(driver, SupplierPage.class);
		boolean res = sup.addsupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
		if(res)
		{
			xl.setcelldata(Tcsheet, i, 9, "pass", outputpath);
			logger.log(LogStatus.PASS, "AddSupplier is Success");
		}
		else
		{
			xl.setcelldata(Tcsheet, i, 9, "fail", outputpath);
			logger.log(LogStatus.FAIL, "AddSupplier is failed");
		}
		reports.endTest(logger);
		reports.flush();
	}
	
}
}
