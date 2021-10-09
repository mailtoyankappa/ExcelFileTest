package com.bezkoder.spring.files.excel.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.excel.model.Hospital;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"source", "codeListCode", "code", "displayValue", "longDescription", "fromDate", "toDate", "sortingPriority"};
    static String SHEET = "exercise";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static ByteArrayInputStream hospitalsToExcel(List<Hospital> hospitals) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Hospital hospital : hospitals) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(hospital.getSource());
                row.createCell(1).setCellValue(hospital.getCodeListCode());
                row.createCell(2).setCellValue(hospital.getCode());
                row.createCell(3).setCellValue(hospital.getDisplayValue());
                row.createCell(4).setCellValue(hospital.getLongDescription());
                row.createCell(5).setCellValue(hospital.getFromDate());
                row.createCell(6).setCellValue(hospital.getToDate());
                row.createCell(7).setCellValue(hospital.getSortingPriority());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Hospital> excelToHospitals(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Hospital> hospitals = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Hospital hospital = new Hospital();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            hospital.setSource(currentCell.getStringCellValue());
                            break;

                        case 1:
                            hospital.setCodeListCode(currentCell.getStringCellValue());
                            break;

                        case 2:
                            hospital.setCode(currentCell.getStringCellValue());
                            break;

                        case 3:
                            hospital.setDisplayValue(currentCell.getStringCellValue());
                            break;
                        case 4:
                            hospital.setLongDescription(currentCell.getStringCellValue());
                            break;

                        case 5:
                            String str = currentCell.getStringCellValue();
                            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(str);
                            hospital.setFromDate(date1);
                            break;

                        case 6:
                            String str1 = currentCell.getStringCellValue();
                            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(str1);
                            hospital.setToDate(date);
                            break;

                        case 7:
                            String str2 = currentCell.getStringCellValue();
                            hospital.setSortingPriority(Integer.parseInt(str2));
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                hospitals.add(hospital);
            }

            workbook.close();

            return hospitals;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
