package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
XSSFWorkbook wb;
//create constructor for class
public ExcelFileUtil(String excelpath) throws Throwable
{
	FileInputStream fi = new FileInputStream(excelpath);
	wb = new XSSFWorkbook(fi);
}
//count no.of rows in sheet
public int rowcount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
	
}
//method for reading celldata
public String getcelldata(String sheetname,int row,int column)
{
	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
	{
		int celldata = (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	   data = String.valueOf(celldata);
	}
	else
	{
		 data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
//method to set cell value and writing status into new wb
public void setcelldata(String sheetname,int row,int column,String status,String write_excelpath) throws Throwable
{
	//get sheet from wb
	XSSFSheet ws = wb.getSheet(sheetname);
	//get row from sheet
	XSSFRow rownum = ws.getRow(row);
	//create cell in rownum
	XSSFCell cell = rownum.createCell(column);
	//write status into cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("fail"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("blocked"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
		}
	FileOutputStream fo = new FileOutputStream(write_excelpath);
	wb.write(fo);
}
public static void main(String[] args) throws Throwable {
	ExcelFileUtil xl = new ExcelFileUtil("D:/practice_sheet.xlsx");
	int rc = xl.rowcount("emp");
	System.out.println("no.of rows are:::"+rc);
	//iterate all rows
	for(int i=1;i<=rc;i++)
	{
		String fname = xl.getcelldata("emp", i, 0);
		String lname = xl.getcelldata("emp", i, 1);
		String eid = xl.getcelldata("emp", i, 2);
		System.out.println(fname+"    "+lname+"   "+eid);
		//setcellvalue as pass,fail,blocked
		xl.setcelldata("emp", i, 3, "pass", "D:/results.xlsx");
		//xl.setcelldata("emp", i, 3, "fail", "D:/results.xlsx");
		//xl.setcelldata("emp", i, 3, "blocked", "D:/results.xlsx");
		
	}
}
}
