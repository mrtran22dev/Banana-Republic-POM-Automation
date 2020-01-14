package org.br.excelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.br.testbase.TestBase;

public class ReadWriteExcelUtil extends TestBase {

	private static final String path = "C:\\Users\\mt\\eclipse-workspace1\\auto-pom\\expectedData.xlsx";
	private Workbook workbook;
	private static Sheet sheet;
	private String pageName;
	private int column;
	private int rowStart;
	private int rowStop;
	
	public ReadWriteExcelUtil(String pageName) {
		try {
			workbook = WorkbookFactory.create(new File(path));
			System.out.println("Number of sheets in excel workbook: " + workbook.getNumberOfSheets());
			this.pageName = pageName;
			sheet = workbook.getSheet(pageName); 
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Excel file/sheet does not exist!");
			e.printStackTrace();
		}
	}

	public void rowColumnSetter(String methodName) {
		String caseName = pageName + "_" + methodName;
		// CHECK: System.out.println("case: " + caseName);
		
		switch (caseName) {
		case "HomePage_getTitleTest":
			System.out.println("HomePage sheet selected");
			rowStart = 1;
			rowStop = 2;
			column = 1;
			break;
		case "HomePage_checkLogosTest":
			rowStart = 2;
			rowStop = 7;
			column = 1;
			break;
		case "HomePage_checkNavBarMenuTest":
			rowStart = 7;
			rowStop = 16;
			column = 1;
			break;
		case "HomePage_checkSignInMenuTest":
			rowStart = 16;
			rowStop = 19;
			column = 1;
			break;
		case "LoginPage_titleTest":
			rowStart = 1;
			rowStop = 2;
			column = 1;
			break;
		case "LoginPage_loginAccountTest":
			rowStart = 2;
			rowStop = 4;
			column = 1;
			break;
		case "LoginPage_accountDropDownMenuTest":
			rowStart = 4;
			rowStop = 10;
			column = 1;
			break;
		case "LoginPage_accountInfoTest":
			rowStart = 2;
			rowStop = 3;
			column = 1;
			break;
		default:
			System.out.println("Page/method " + caseName + " not found. Default row/column setup");
			break;
		}
	}
	
	public ArrayList<String> getExpectedData(String methodName) {
		Row row;
		Cell cell;
		ArrayList<String> dataList = new ArrayList<String>();
		rowColumnSetter(methodName);
		
		for (int i=rowStart;i<rowStop;i++) {
			row = sheet.getRow(i);
			cell = row.getCell(column);
			dataList.add(cell.toString());		
			// CHECK: System.out.println(dataList.get(i-rowStart));
		}
		return dataList;
	}
	
	public void checkPassFail(int index, String actual, String expected) {
		int indexAdjusted = index+rowStart;
		if (actual.equals(expected)) {
			setPassFail(indexAdjusted, actual, "PASS");
		} else {
			setPassFail(indexAdjusted, actual, "FAIL");
		}		
	}
	
	public void setPassFail(int index, String actual, String result) {
		try {
			FileInputStream is = new FileInputStream(new File(path));
			Workbook workbook = WorkbookFactory.create(is);	
			sheet = workbook.getSheet(pageName);
			Row row;
			Cell cell;
			column = 1;
			
			// append PASS/FAIL to same row, new column
			row = sheet.getRow(index);
			cell = row.createCell(++column);
			cell.setCellValue(actual);
			cell = row.createCell(++column);
			cell.setCellValue(result);
			is.close();
			
			FileOutputStream fos = new FileOutputStream(path);
			workbook.write(fos);
			workbook.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	
}
