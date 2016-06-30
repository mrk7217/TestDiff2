import java.io.*;
import java.net.*;
 
public class WGet {
  public static void main(String[] args) throws Exception {
    String s;
    //reads pom files as a different format due to java compiler?
    String urlName = "https://repo1.maven.org/maven2/cglib/cglib-nodep/2.2/cglib-nodep-2.2.pom";
    BufferedReader r = new BufferedReader(new InputStreamReader(new URL(urlName).openStream()));
    
    String fileName = urlName.substring(urlName.lastIndexOf('/') + 1);
    String fileLoc = "files/";
    String fileDest = fileLoc + fileName;
    File file = new File(fileDest);
    
    //may be an issue with similar or identical urls, need to add program control for these possibilities
    if (!file.exists()){ //should add else for possibility of same file--would have same end extension
    	PrintWriter writer = new PrintWriter(file);
    	while ((s = r.readLine()) != null) {
    		System.out.println(s); //prints what it is writing to file
    		writer.println(s);
    	}
    	writer.close();}
    
    
  }
}
