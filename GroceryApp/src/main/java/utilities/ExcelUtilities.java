package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import utilities.FilePaths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")
public class ExcelUtilities {

	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static FileInputStream f;

	public static String getString(int i, int j, String sheet) {
		String file_path = FilePaths.TESTDATAFILE;
		try {
			f = new FileInputStream(file_path);

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sh = wb.getSheet(sheet);
		Row r = sh.getRow(i);
		Cell c = r.getCell(j);
		return c.getStringCellValue();

	}

	public static int getInt(int i, int j, String sheet) {
		String filePath = FilePaths.TESTDATAFILE;
		int cellValue = 0;

		try {
			f = new FileInputStream(filePath);
			wb = new XSSFWorkbook(f);
			sh = wb.getSheet(sheet);

			Row row = sh.getRow(i);
			if (row != null) {
				Cell cell = row.getCell(j);
				if (cell != null) {
					// Check if the cell contains a numeric value
					if (cell.getCellType() == CellType.NUMERIC) {
						cellValue = (int) cell.getNumericCellValue();
					} else if (cell.getCellType() == CellType.STRING) {
						// If the cell contains a string, attempt to parse it as an integer
						cellValue = Integer.parseInt(cell.getStringCellValue());
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // Handle File Not Found
		} catch (IOException e) {
			e.printStackTrace(); // Handle IO Exception
		} catch (NumberFormatException e) {
			e.printStackTrace(); // Handle the case where the cell string cannot be parsed as an integer
		} finally {
			try {
				if (wb != null) {
					wb.close();
				}
				if (f != null) {
					f.close();
				}
			} catch (IOException e) {
				e.printStackTrace(); // Handle Exception during close
			}
		}
		return cellValue;
	}

	public void saveTableContentsToNewExcelFile(List<WebElement> tableRows, String pageName, String className)
			throws IOException {
		// Create a new workbook and sheet
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(pageName);

		// Loop through table rows and cells, adding them to the Excel sheet
		for (int i = 0; i < tableRows.size(); i++) {
			Row row = sheet.createRow(i);
			List<WebElement> cells = tableRows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cells.size(); j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(cells.get(j).getText());
			}
		}

		// Create a directory if it doesn't exist
		File directory = new File(System.getProperty("user.dir") + "\\OutputData");
		if (!directory.exists()) {
			directory.mkdirs();
		}

		// Create a new file with a unique timestamp in the filename
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
		File fileOut = new File(directory.getPath() + "\\" + className + timeStamp + ".xlsx");

		// Write the workbook to the file
		try (FileOutputStream fos = new FileOutputStream(fileOut)) {
			workbook.write(fos);
		}

		// Close the workbook
		workbook.close();
	}

}
