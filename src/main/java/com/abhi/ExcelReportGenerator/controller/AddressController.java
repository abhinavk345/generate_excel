package com.abhi.ExcelReportGenerator.controller;

import com.abhi.ExcelReportGenerator.service.AddressService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
}
