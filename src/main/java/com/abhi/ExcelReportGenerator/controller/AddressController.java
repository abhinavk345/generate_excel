package com.abhi.ExcelReportGenerator.controller;

import com.abhi.ExcelReportGenerator.service.AddressService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/report")
    public void generateExcelReport(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/octet-stream");
        String key="Content-Disposition";
        String value="attachment;filename=address.xls";
        httpServletResponse.setHeader(key,value);
        addressService.generateReport(httpServletResponse);

    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadExcelReport() throws IOException {

        ByteArrayInputStream byteArrayInputStream =addressService.getReport();
        InputStreamResource response=new InputStreamResource(byteArrayInputStream);
        String fileName="addressData.xls";
        ResponseEntity<InputStreamResource> resourceResponseEntity=
                ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename"+fileName)
                        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                        .body(response);

        return resourceResponseEntity;

    }


}
