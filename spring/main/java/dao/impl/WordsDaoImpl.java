package dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import dao.IWordsDao;
import domain.Word;

@Component
public class WordsDaoImpl implements IWordsDao{
	
	private final String PATH = "D:/env/workspace/spring/data";
	private Vector<Map<Integer,String>> excelData = new Vector<Map<Integer,String>>();
	private static Logger logger = Logger.getLogger(Class.class.getName());
	
	@Override
	public List<Word> getWordsByLevel(int level) {
		loadData();
		List<Word> list = new LinkedList<Word>();
		
		for(Map<Integer,String> rowMap:excelData){
			int cellLevel= new Double(rowMap.get(0)).intValue();
			if(cellLevel==level){
				Word word = new Word();
				word.setFirstHalf(rowMap.get(1));
				word.setLastHalf(rowMap.get(2));
				list.add(word);
			}
		}
		
		return list;
	}
	
	
	private void loadData(){
		File dir = new File(PATH);
		
		for(File file:dir.listFiles()){
			try{
				excelData.addAll(getExcelData(file));
			}catch(IOException ex){
				logger.info(ex.getMessage());
			}
		}
		
	}
	
	

	private Vector<Map<Integer,String>> getExcelData(File file) throws IOException{
		
		FileInputStream ins = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(ins);  
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Vector<Map<Integer,String>> perFileVector = new Vector<Map<Integer,String>>();
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.iterator();
			Map<Integer,String> map = new HashMap<Integer,String>();
			while(cellIterator.hasNext()){
				String cellValue = "";
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					cellValue = String.valueOf(cell.getNumericCellValue());
					break;
				default:
					break;
				}
				
				map.put(cell.getColumnIndex(), cellValue);
				
			}
			
			perFileVector.add(map);
			
		}
		
		return perFileVector;
	}
	
}
