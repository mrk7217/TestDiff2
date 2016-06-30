import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.opencsv.CSVReader;

public class FileRead {
	private final String csvFilename = "/Users/margaretknoblock/Downloads/otherLic.csv";
	List<String[]> fullFile = null;
	String[] tempRow = null;
	
	public String accessFile(int row, int col){
		String info = new String();
		try{
			FileReader read = new FileReader(csvFilename); 
			CSVReader csvReader = new CSVReader(read);
			fullFile = csvReader.readAll();
			if (row >0){ //row can not be the title row
				tempRow = (String[]) fullFile.get(row);
				info = tempRow[col]; //access only the desired column
			}
			csvReader.close();
			return info;
		} 
		catch(IOException ex){	
			ex.printStackTrace();
			return null;
		}
	}
	
	public int numRows(){
		return fullFile.size(); //only gets the size of a null fullFile, need to fix this
	}
}
