package com.covidchecklist.app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.covidchecklist.app.entities.Employee;

@Service
public class ReadExcelService {
 
	public List< Employee> readDataFromExcel() {
		List< Employee> employees = new ArrayList<Employee>();
		try {
			FileInputStream file = new FileInputStream(new File(
					"SMTP.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				int id=0;
				String empId = "";
				String empName = "";
				String empMail = "";
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				if(row.getRowNum() != 0)
				{
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						if (empId.equalsIgnoreCase("")) {
							id=  (int) cell.getNumericCellValue();
 							empId = id + "";
						} 
						break;
					case Cell.CELL_TYPE_STRING:
						if (cell.getStringCellValue().trim().equalsIgnoreCase("External") || 
								empId.equalsIgnoreCase("")) {
							// 2nd column
							empId= cell.getStringCellValue().trim();

						} 
						else if (empName.equalsIgnoreCase("")) {
							// 2nd column
							empName = cell.getStringCellValue().trim();

						} else if (empMail.equalsIgnoreCase("")) {
							// 3rd column
							empMail = cell.getStringCellValue().trim();
						} else {
							// random data, leave it
							System.out.println("Random data::" + cell.getStringCellValue());
						}

						break;
					}
				}
				Employee employee = new Employee(empId, empName, empMail);
				employees.add(employee);
				System.out.println(employee.toString());
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}
	//For Testing purpose only 
//	public static void main(String[] args) {
//		ReadExcelService excel = new ReadExcelService();
//		 excel.readDataFromExcel();
//	}
}
