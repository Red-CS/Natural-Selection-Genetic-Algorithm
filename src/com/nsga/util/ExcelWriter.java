package com.nsga.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 22, 2021
 */
public class ExcelWriter {

    private XSSFWorkbook workbook;
    private XSSFSheet spreadsheet;
    private XSSFRow row;

    private File file;
    private String fileName;

    /**
     * ExcelWriter Class Constructor
     * @param fileName
     * @param sheetName
     * @param genHeader
     * @param fitnessHeader
     * @throws IOException
     */
    public ExcelWriter(
        String fileName,
        String sheetName,
        String genHeader,
        String fitnessHeader)
        throws IOException {
        // TODO Auto-generated constructor stub
        // workbook object
        workbook = new XSSFWorkbook();

        // spreadsheet object
        spreadsheet = workbook.createSheet(sheetName);

        // creating a row object
        row = spreadsheet.createRow(0);
        row.createCell(0).setCellValue(genHeader);
        row.createCell(1).setCellValue(fitnessHeader);

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        file = new File(fileName);
        this.fileName = fileName;
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
    }


    public void logGeneration(int generationNumber, double averageFitness) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(
                fileName));
            Workbook workbook;
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.createRow(generationNumber);
            row.createCell(0).setCellValue(generationNumber);
            row.createCell(1).setCellValue(averageFitness);
            inputStream.close();
            FileOutputStream out = new FileOutputStream(fileName);
            workbook.write(out);
            out.close();
        }
        catch (InvalidFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
// System.exit(0);

    }

}
