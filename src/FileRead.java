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
	final int licenseRow = 5;
	final int licenseDetailedRow = 6;
	final int urlRow = 7;
	 
	@Test
	public void accessFile(){
		try{
			FileReader read = new FileReader(csvFilename); //need to figure out how to fix thissss
			CSVReader csvReader = new CSVReader(read);
			fullFile = csvReader.readAll();
			for(int i=1;i<10;i++){ //starts at one to skip the titles of each column
				tempRow = (String[]) fullFile.get(i);
				System.out.println(tempRow[urlRow]); //access only the desired column
				
			}
			csvReader.close();
		} 
		catch(IOException ex){	
			ex.printStackTrace();
		}
	}
}
