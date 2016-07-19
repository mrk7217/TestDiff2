import java.io.*;
import java.net.*;
 
public class WGet { //for saving files from a given url
	public File getFile(String urlName) {
		try{
			String s;
			BufferedReader r = new BufferedReader(new InputStreamReader(new URL(urlName).openStream()));
    
			String fileName = urlName.substring(urlName.lastIndexOf('/') + 1); //makes name of file the final section of the url after the last "/"
			String fileLoc = "files/"; //sets location for file to be saved
			String fileDest = fileLoc + fileName; //sets full file name with destination included
			File file = new File(fileDest); // creates file
    
			if (!file.exists()){ //will not write file again if file already exists
				PrintWriter writer = new PrintWriter(file);
				while ((s = r.readLine()) != null) { //while lines are not null
					writer.println(s); //writes lines to the file
				}
				writer.close(); //closes file writer
			}
			return file;
		}
		catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
	}  
	
	public String getFilePath(File file){
		String filePath = file.getAbsolutePath();
		return filePath;
	}
  
  
}

