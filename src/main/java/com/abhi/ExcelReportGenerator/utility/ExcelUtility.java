package com.abhi.ExcelReportGenerator.utility;

import com.abhi.ExcelReportGenerator.entity.Address;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtility {
    public static String HEADER[]={"ID","STREET_NO","CITY","STATE","COUNTRY"};
    public static String SHEET_NAME="addressSheet";

    public static ByteArrayInputStream dataToExcel(List<Address> addressList) throws IOException {
        Workbook workbook=new HSSFWorkbook();
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        Sheet sheet = workbook.createSheet(SHEET_NAME);
        Row row = sheet.createRow(0);
        try {
            int cellHeaderIndex=0;
        for(String createCell:HEADER){
            row.createCell(cellHeaderIndex++).setCellValue(createCell);
        }
        int rowIndex=1;
        for(Address address:addressList){
            Row row1 = sheet.createRow(rowIndex++);
            int cellIndex=0;
            row1.createCell(cellIndex++).setCellValue(address.getId());
            row1.createCell(cellIndex++).setCellValue(address.getStreetNo());
            row1.createCell(cellIndex++).setCellValue(address.getCity());
            row1.createCell(cellIndex++).setCellValue(address.getState());
            row1.createCell(cellIndex).setCellValue(address.getCountry());

        }

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            outputStream.close();
            workbook.close();
        }

    }
}
