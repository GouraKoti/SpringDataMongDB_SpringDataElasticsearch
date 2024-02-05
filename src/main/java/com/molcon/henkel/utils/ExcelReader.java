package com.molcon.henkel.utils;

import com.molcon.henkel.entity.MongoHenkel;
import com.molcon.henkel.repo.MongoRepo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ExcelReader {

      public void readExcel(MultipartFile file, MongoRepo mongoRepo) throws IOException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
          Workbook workbook = new XSSFWorkbook(file.getInputStream());

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

          // Using a for-each loop to iterate over the rows and columns
        for (Row row : sheet) {
            String mcid = "";
            if (row.getCell(0) != null) {
                mcid=row.getCell(0).getStringCellValue();
            }

            String commonName = "";
            if (row.getCell(1) != null) {
                commonName = row.getCell(1).getStringCellValue();
            }

            String description = "";
            if (row.getCell(2) != null) {
                description = row.getCell(2).getStringCellValue();
            }

            String sapCs = "";
            if (row.getCell(3) != null) {
                sapCs = row.getCell(3).getStringCellValue();
            }

            MongoHenkel henkel = new MongoHenkel(mcid, commonName, description, sapCs);
            mongoRepo.save(henkel);
        }

        // Closing the workbook
        workbook.close();
    }
}