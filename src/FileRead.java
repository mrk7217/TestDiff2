import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class FileRead { //reads in information from spreadsheet with all other license information
	List<String[]> fullFile;
	String[] tempRow = null;
	int numRows;
	int numCols;
	CSVReader csvReader;
	
	public void openFile(String csvFilename){ //opens the spreadsheet
		try{
			FileReader read = new FileReader(csvFilename); 
			CSVReader csvReader = new CSVReader(read);
			this.csvReader = csvReader;
			fullFile = csvReader.readAll();
			System.out.println("file opened");
		}
		catch(IOException ex){	
			ex.printStackTrace();
		}
	}
	
	public String accessFile(int row, int col){ //returns desired cell in spreadsheet
		String info = new String();
		tempRow = getRow(row);
		info = tempRow[col]; 
		return info;
	}
	
	public String[] getRow(int row){ //returns desired row from spreadsheet as string array
		String[] currentRow = (String[]) fullFile.get(row);
		return currentRow;
	}
	
	public int numCols(){ //returns number of columns from file
		numCols = fullFile.get(0).length;
		return numCols;
	}
	
	public int numRows(){ //returns number of rows from file
		numRows = fullFile.size();
		return numRows;
	}
	
	public void closeFile(){ //closes file
		try {
			csvReader.close();
			System.out.println("file closed");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
