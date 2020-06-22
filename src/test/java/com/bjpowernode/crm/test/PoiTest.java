package com.bjpowernode.crm.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.*;

/**
 * lzx
 * 2020/3/5
 */
public class PoiTest {

    @Test
    public void testCreateExcel() throws IOException {

        // 对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 页
        HSSFSheet sheet = workbook.createSheet("学生列表");
        //行
        HSSFRow row = sheet.createRow(0);
        //列
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("学号");
        cell=row.createCell(1);
        cell.setCellValue("姓名");
        cell=row.createCell(2);
        cell.setCellValue("年龄");


        for (int i = 0; i <=10; i++) {

           row= sheet.createRow(i);

           cell=row.createCell(0);
           cell.setCellValue(100+i);
           cell=row.createCell(1);
           cell.setCellValue("name"+i);
           cell=row.createCell(2);
           cell.setCellValue(20+i);
        }

        FileOutputStream os = new FileOutputStream("E:\\tools\\student.xls");
        workbook.write(os);
        os.close();
        workbook.close();



    }
    @Test
    public void testParseExcel() throws IOException {

        InputStream is = new FileInputStream("E:\\tools\\student.xls");
        //创建Workbook对象，对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row=null;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            row=sheet.getRow(i);

            HSSFCell cell=null;
            for (int j = 0; j <row.getLastCellNum(); j++) {

                cell=row.getCell(j);

                System.out.print(getValueFromCell(cell)+" ");

            }
            System.out.println();
        }
    }

    public String getValueFromCell(HSSFCell cell){

        int cellTye=cell.getCellType();

        String ret="";
        switch (cellTye){
            case HSSFCell.CELL_TYPE_BLANK:
                ret="";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                ret=cell.getBooleanCellValue()+"";
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                ret="";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                ret=cell.getCellFormula();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                ret=cell.getNumericCellValue()+"";
                break;
            case HSSFCell.CELL_TYPE_STRING:
                ret=cell.getStringCellValue();
        }
        return ret;

    }
}
