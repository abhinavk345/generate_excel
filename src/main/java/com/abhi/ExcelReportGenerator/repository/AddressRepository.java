package com.abhi.ExcelReportGenerator.repository;

import com.abhi.ExcelReportGenerator.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address,Long> {
}
