package com.abhi.ExcelReportGenerator.service;

import com.abhi.ExcelReportGenerator.entity.Address;
import com.abhi.ExcelReportGenerator.repository.AddressRepository;
import com.abhi.ExcelReportGenerator.utility.ExcelUtility;
import jakarta.persistence.Column;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void generateReport(HttpServletResponse httpServletResponse) throws IOException {

        List<Address> addressList = addressRepository.findAll();

        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("report");
        HSSFRow headerRow = sheet.createRow(0);

        int columnIndexHeader=0;
        headerRow.createCell(columnIndexHeader++).setCellValue("ID");
        headerRow.createCell(columnIndexHeader++).setCellValue("STREET_NO");
        headerRow.createCell(columnIndexHeader++).setCellValue("CITY");
        headerRow.createCell(columnIndexHeader++).setCellValue("STATE");
        headerRow.createCell(columnIndexHeader).setCellValue("COUNTRY");

        int columnIndex=1;
        for(Address address:addressList){
            HSSFRow dataRow = sheet.createRow(columnIndex++);
            int cellIndex=0;
            dataRow.createCell(cellIndex++).setCellValue(address.getId());
            dataRow.createCell(cellIndex++).setCellValue(address.getStreetNo());
            dataRow.createCell(cellIndex++).setCellValue(address.getCity());
            dataRow.createCell(cellIndex++).setCellValue(address.getState());
            dataRow.createCell(cellIndex).setCellValue(address.getCountry());

        }
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();


    }

    public ByteArrayInputStream getReport() throws IOException {
        List<Address> addressList = addressRepository.findAll();
        ByteArrayInputStream data=ExcelUtility.dataToExcel(addressList);
        return  data;

    }
}
