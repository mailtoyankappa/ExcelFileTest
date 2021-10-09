package com.bezkoder.spring.files.excel.service;

import java.io.*;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.excel.helper.ExcelHelper;
import com.bezkoder.spring.files.excel.model.Hospital;
import com.bezkoder.spring.files.excel.repository.HospitalRepository;

@Service
public class ExcelService {
  @Autowired
  HospitalRepository repository;

  @Value(value = "${xlsx.data.file.location}")
  private String XLSX_DATA_FILE;

  public void save(MultipartFile file) {
    try {
      List<Hospital> hospitals = ExcelHelper.excelToHospitals(file.getInputStream());
      repository.saveAll(hospitals);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public void saveUsingLoad() throws FileNotFoundException {
    FileInputStream xlsFile = new FileInputStream(new File(XLSX_DATA_FILE));
    List<Hospital> hospitals = ExcelHelper.excelToHospitals(xlsFile);
    repository.saveAll(hospitals);
  }


  public ByteArrayInputStream load() {
    List<Hospital> hospitals = repository.findAll();

    ByteArrayInputStream in = ExcelHelper.hospitalsToExcel(hospitals);
    return in;
  }

  public List<Hospital> getAllTutorials() {
    return repository.findAll();
  }
}
