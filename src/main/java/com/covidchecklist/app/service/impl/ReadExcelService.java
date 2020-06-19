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

import com.covidchecklist.app.entities.AnswerOptions;
import com.covidchecklist.app.entities.Employee;
import com.covidchecklist.app.entities.QAndA;
import com.covidchecklist.app.entities.Question;

@Service
public class ReadExcelService {

	public List<QAndA> readQAndADataFromExcel() {
		List<QAndA> qAndAs = new ArrayList<QAndA>();
		try {
			FileInputStream file = new FileInputStream(new File("Questions_list.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				int id = 0;
				String ques = "";
				String ans_option1 = "";
				String ans_option2 = "";
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				if (row.getRowNum() != 0) {
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
						
						case Cell.CELL_TYPE_STRING:
							if (ques.equalsIgnoreCase("")) {
								// 2nd column
								ques = cell.getStringCellValue().trim();

							} else if (ans_option1.equalsIgnoreCase("")) {
								// 2nd column
								ans_option1 = cell.getStringCellValue().trim();

							} else if (ans_option2.equalsIgnoreCase("")) {
								// 3rd column
								ans_option2 = cell.getStringCellValue().trim();
							} else {
								// random data, leave it
								System.out.println("Random data::" + cell.getStringCellValue());
							}

							break;
						}
					}
					Question question = new Question();
					question.setQuestion(ques);
					
					AnswerOptions option1 = new AnswerOptions();
					option1.setOption(ans_option1);
					AnswerOptions option2 = new AnswerOptions();
					option2.setOption(ans_option2);
					
					QAndA qAndA = new QAndA();
					qAndA.setQuestion(question);
					qAndA.setOption1(option1);
					qAndA.setOption2(option2);
					qAndAs.add(qAndA);
					System.out.println(qAndA.toString());
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return qAndAs;
	}

	public List<Employee> readEmployeeDataFromExcel() {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			FileInputStream file = new FileInputStream(new File("SMTP.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				int id = 0;
				String empId = "";
				String empName = "";
				String empMail = "";
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				if (row.getRowNum() != 0) {
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							if (empId.equalsIgnoreCase("")) {
								id = (int) cell.getNumericCellValue();
								empId = id + "";
							}
							break;
						case Cell.CELL_TYPE_STRING:
							if (cell.getStringCellValue().trim().equalsIgnoreCase("External")
									|| empId.equalsIgnoreCase("")) {
								// 2nd column
								empId = cell.getStringCellValue().trim();

							} else if (empName.equalsIgnoreCase("")) {
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
	// For Testing purpose only
	public static void main(String[] args) {
		ReadExcelService excel = new ReadExcelService();
		 //excel.readDataFromExcel();
		excel.readQAndADataFromExcel();
	}
}
