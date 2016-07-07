import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.opencsv.CSVReader;

public class FileRead { //for spreadsheet with other licenses
	private final String csvFilename = "/Users/margaretknoblock/Downloads/otherLic.csv";
	List<String[]> fullFile;
	String[] tempRow = null;
	int numRows;
	int numCols;
	CSVReader csvReader;
	
	public void openFile(){
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
	
	public String accessFile(int row, int col){
		String info = new String();
		
		tempRow = (String[]) fullFile.get(row);
		info = tempRow[col]; //access only the desired column
		
		return info;
	}
	
	public void closeFile(){
		try {
			csvReader.close();
			System.out.println("file closed");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int numCols(){
		numCols = fullFile.get(0).length;
		return numCols;
	}
	
	public int numRows(){
		numRows = fullFile.size();
		return numRows; //only gets the size of a null fullFile, need to fix this
	}
}
