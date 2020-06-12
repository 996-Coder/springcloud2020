package com.practice.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author michael.zhang
 * @date 6/5/2020 11:28
 * @desc Apache POI 
 */
public class Demo {
    public static void main(String[] args) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet0 = workbook.createSheet("sheet0");
        HSSFRow row = sheet0.createRow(0);
        HSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("dadasda");
        try {
            FileOutputStream fos = new FileOutputStream("d:\\demo.xls");
            workbook.write(fos);
            fos.flush();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
