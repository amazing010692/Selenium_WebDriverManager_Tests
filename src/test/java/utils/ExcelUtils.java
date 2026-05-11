package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.ConfigReader;

/**
 * Reusable Excel data reader for data-driven testing.
 */
public class ExcelUtils {

    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);

    public static List<String> getTestData(String filePath, String sheetName, String testCaseName)
            throws IOException {
        List<String> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                logger.error("Sheet '{}' not found in file: {}", sheetName, filePath);
                return data;
            }

            int testCaseColumn = findColumnIndex(sheet, "Testcases");
            if (testCaseColumn == -1) {
                logger.error("'Testcases' column not found in sheet: {}", sheetName);
                return data;
            }

            Iterator<Row> rows = sheet.iterator();
            rows.next(); // skip header row

            while (rows.hasNext()) {
                Row row = rows.next();
                Cell cell = row.getCell(testCaseColumn);
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
                    Iterator<Cell> cells = row.cellIterator();
                    while (cells.hasNext()) {
                        Cell c = cells.next();
                        data.add(getCellValueAsString(c));
                    }
                    break;
                }
            }
        }

        logger.info("Retrieved {} data fields for test case: {}", data.size(), testCaseName);
        return data;
    }

    private static int findColumnIndex(XSSFSheet sheet, String columnName) {
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) return -1;

        Iterator<Cell> cells = headerRow.cellIterator();
        int index = 0;
        while (cells.hasNext()) {
            if (cells.next().getStringCellValue().equalsIgnoreCase(columnName)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return NumberToTextConverter.toText(cell.getNumericCellValue());
        }
        return "";
    }
}
