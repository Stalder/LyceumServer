import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

/**
 * Created by Sergey Lavinov on 02.08.2015.
 */
public class ExcelManager{

    private Workbook workbook;

    public void openFile(String fileName) throws IOException{

        FileInputStream excelFile = new FileInputStream(fileName);
        workbook = new HSSFWorkbook(excelFile);

    }

    public void readPupilFromFile(){
        System.out.println(workbook.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());
    }

    public void closeFile() throws IOException{
        workbook.close();
    }

    public String getCellStringValue(int row, int column){
        String value = workbook.getSheetAt(0).getRow(row).getCell(column).getStringCellValue();
        return value;
    }

    public int getCellIntValue(int row, int column){
        int value = (int)workbook.getSheetAt(0).getRow(row).getCell(column).getNumericCellValue();
        return value;
    }

    public Workbook getWorkbook() {
        return workbook;
    }
}
