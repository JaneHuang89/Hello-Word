package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author YR
 *
 */
public class ExcelStudy {
	
	public static void main(String args[]) throws InvalidFormatException, IOException{
//		createExcelFile();
//		openExcel();
		
		Object[][] obj = testData("E://code//exceltest.xlsx","test");
		for(Object[] i : obj){
			for(Object j : i){
				System.out.print(j + "| |");
			}
			System.out.println();
		}
	}
	
	
	public static void createExcelFile(){
		XSSFWorkbook wb = new XSSFWorkbook(); //create excel file
//		Sheet sheet1 = wb.createSheet("sheet2");//create sheet for the file
//		Sheet sheet2 = wb.createSheet("hahhh");
		Sheet sheet3 = wb.createSheet("测试kankan");
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("E://code//exceltest.xlsx");
			wb.write(out);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				out.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}		
	}
	
	public static void openExcel(){
		FileInputStream in = null;
		Workbook wb = null;
		try {
			in = new FileInputStream("E://code//exceltest.xlsx");
			wb = WorkbookFactory.create(in);
			System.out.println(wb.getSheetName(0));
			
			
			// read value in the cell
			Row row = wb.getSheetAt(0).getRow(0);
			for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
				System.out.println(wb.getSheetAt(0).getRow(0).getCell(i));
			}
			
			
			//write value in the cell
			System.out.println("start......");			
			Row row2 = wb.getSheetAt(0).createRow(1);
			for (int i = 0; i < 10; i++) {
				System.out.println(i);				
				row2.createCell(i).setCellValue("嘎嘎嘎" + i);				
			}
			FileOutputStream out = new FileOutputStream("E://code//exceltest.xlsx");
			wb.write(out);
			out.close();
			System.out.println("end......");
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch(InvalidFormatException e1){
			System.out.println(e1.getMessage());
		} finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//读取sheet表cell中的数据，并返回一个object二维数组
	public static Object[][] testData(String filepath,String sheetname) throws InvalidFormatException, IOException{
		ArrayList<Object> array = new ArrayList<Object>();
		FileInputStream in = new FileInputStream(filepath);
		Workbook wb = WorkbookFactory.create(in);
		Sheet sheet = wb.getSheet(sheetname);
		//判断sheet表中有数据
		if(sheet.getPhysicalNumberOfRows()!=0){
			//将execl数据按照行，保存到arraylist中
			for(int i = 1; i <= sheet.getLastRowNum(); i++){
				int len = sheet.getRow(i).getLastCellNum();
				Object[] rowobj = new Object[len];
				for(int j = 0; j < sheet.getRow(i).getLastCellNum(); j++){
					//将cell中的内容转化为string格式，切去掉前后空格
					rowobj[j] = getCellValue(sheet.getRow(i).getCell(j));					
				}
				array.add(rowobj);
			}
		}else{
			System.out.println("这个sheet没有数据");
		}
		
		//将arraylist 转换为二位数组
		Object[][] objs = new Object[array.size()][];
		for(int i=0; i < array.size();i++){
			 objs[i]= (Object[]) array.get(i);
		}	
		in.close();
		return objs;
	}
			
	//对Excel的各个单元格的格式进行判断并转换为string
	public static String getCellValue(Cell cell) {   
        String cellValue = "";   
        DecimalFormat df = new DecimalFormat("#");   
        switch (cell.getCellType()) {   
        case Cell.CELL_TYPE_STRING:   
            cellValue =cell.getRichStringCellValue().getString().trim();   
            break;   
        case Cell.CELL_TYPE_NUMERIC:   
            cellValue =df.format(cell.getNumericCellValue()).toString();   
            break;   
        case Cell.CELL_TYPE_BOOLEAN:   
            cellValue =String.valueOf(cell.getBooleanCellValue()).trim();   
            break;   
        case Cell.CELL_TYPE_FORMULA:   
            cellValue =cell.getCellFormula();   
            break;   
        default:   
            cellValue = "";   
        }   
        return cellValue;   
    }
	
}
