package fr.ensma.a3.ia.exempledao.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class AbstractPoiDAO<T> implements IDao<T> {

	//private static final String FILE__NAME = System.getProperty("user.home") + "/memorydata/BDD.xlsx";
	private static final String FILE__NAME = System.getProperty("user.dir") + "/memorydata/BDD.xlsx";
	
	protected XSSFWorkbook openBase() {
		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE__NAME));
			return new XSSFWorkbook(excelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected void writeBase(XSSFWorkbook bdd) {		
		try {
			FileOutputStream output = new FileOutputStream(FILE__NAME);
			bdd.write(output);
			bdd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected static void removeRow(Sheet sheet, int rowIndex) {
	    int lastRowNum = sheet.getLastRowNum();
	    if (rowIndex >= 0 && rowIndex < lastRowNum) {
	        sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
	    }
	    if (rowIndex == lastRowNum) {
	        Row removingRow = sheet.getRow(rowIndex);
	        if (removingRow != null) {
	            sheet.removeRow(removingRow);
	        }
	    }
	}
	
}
