import java.io.File;
import java.io.FileReader;

import com.opencsv.CSVReader;

public class FileRead {
	String csvFilename = new String();
	File newFile = new File(csvFilename);
	FileReader read = new FileReader(newFile); //need to figure out how to fix thissss
	CSVReader csvReader = new CSVReader(read);
}
