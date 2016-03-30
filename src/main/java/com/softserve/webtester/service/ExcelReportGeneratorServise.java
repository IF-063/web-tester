package com.softserve.webtester.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.webtester.dto.StatisticDataDTO;
import com.softserve.webtester.dto.StatisticFilterDTO;

@Service
public class ExcelReportGeneratorServise {
    
    @Autowired
    ReportService reportService;
    
    public byte[] GenerateExcelReport(StatisticFilterDTO statisticFilterDTO) {
        int k;
        List<String> buildVersionName = reportService.loadBuildVersionsName(statisticFilterDTO);
        List<StatisticDataDTO> statisticData = reportService.loadStatisticReportData(statisticFilterDTO);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("StatisticReport");
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFRow row = spreadsheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("Service Name");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("SLA");
        cell.setCellStyle(style);
        for (k = 0; k < buildVersionName.size(); k++) {
            cell = row.createCell(2 + k);
            cell.setCellValue(buildVersionName.get(k));
            cell.setCellStyle(style);
        }
        cell = row.createCell(2 + k);
        cell.setCellValue("Average for the last three releases");
        cell.setCellStyle(style);
        for (int l = 0; l <=2+k; l++){
            spreadsheet.autoSizeColumn(l);
            
        }
        for (int i = 0; i < statisticData.size(); i++) {
            int j;
            row = spreadsheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(statisticData.get(i).getServiceName());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(statisticData.get(i).getSla());
            cell.setCellStyle(style);
            for (j = 0; j < buildVersionName.size(); j++) {
                cell = row.createCell(j + 2);
                cell.setCellValue(statisticData.get(i).getResponseTimes().get(j));
                cell.setCellStyle(style);
            }
            cell = row.createCell(2 + j);
            cell.setCellValue(statisticData.get(i).getAverageResponseTime());
            cell.setCellStyle(style);
        }
        // Create file system using specific name
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            workbook.write(baos);
            byte [] xls = baos.toByteArray();
            baos.close();
            return xls;
            //FileOutputStream out = new FileOutputStream(new File("createworkbook.xlsx"));
            // write operation workbook using file out object
            //workbook.write(out);  
            //out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
