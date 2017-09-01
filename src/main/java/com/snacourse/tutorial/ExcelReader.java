package com.snacourse.tutorial;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ExcelReader {





    public List<Product>getAllProduct() {

        String FILE_NAME = "C:\\Users\\Sh-Java\\Desktop\\inventory.xlsx";
        List<Product> productList=new ArrayList<Product>();

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            iterator.next();
            int i = 0;
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();


                String name = currentRow.getCell(0).getStringCellValue();
                String descryption = currentRow.getCell(1).getStringCellValue();
                String price = currentRow.getCell(2).getStringCellValue();

                Product product=new Product();
                product.setName(name );
                product.setDescryption(descryption );
                product.setPrice(price);
                productList.add(product);
                //  String shomareKarvan=    currentRow.getCell(4).getStringCellValue();



                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


return productList;
    }


}
