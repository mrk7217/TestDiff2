import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class NonPOMFile {
	
	private File nonPOMFile;   
	
	
	public NonPOMFile(File nonPOMFile){
		this.nonPOMFile = nonPOMFile;
	}
	
	public static void main(String[] args) throws IOException{
		NonPOMFile nonPOM = new NonPOMFile(new File("/Users/maramuslea/Documents/examplePOMFile.txt"));
		String content = nonPOM.readPageToString();
		nonPOM.printLicense(content);
	}
	
	
	public String readPageToString() throws FileNotFoundException {
		Scanner exampleFile = new Scanner(nonPOMFile); 
		String result = "";
		
		while(exampleFile.hasNextLine()){ 
			result += exampleFile.nextLine() + "\n";
		}
		exampleFile.close();
		
		return result.toLowerCase();
	}
	
	public void printLicense(String a){
        boolean isThereALicense = false; 
		
        if((a.contains("apache"))){
        	System.out.println("Apache 2.0");
        	isThereALicense = true;
        }
        
        if((a.contains("lgpl"))){
        	System.out.println("LGPL 2.1");
        	isThereALicense = true;
        }
        
        if((a.contains("bsd"))){
        	System.out.print("BSD ");
        	
        	if(a.contains("3-clause")){
        		System.out.println("3-Clause");
        		isThereALicense = true;
        	}
        	
        	if(a.contains("2-clause")){
        		System.out.print("2-Clause");
        		isThereALicense = true;
        	}
        }
        
        if((a.contains("epl"))){
        	System.out.println("EPL 1.0");
        	isThereALicense = true;
        }
        
        if((a.contains("gpl"))){
        	System.out.println("GPL 2.0");
        	isThereALicense = true;
        }
        
        if((a.contains("cddl"))){
        	System.out.println("CDDL 1.0");
        	isThereALicense = true;
        }
        
        if((a.contains("afl"))){
        	System.out.println("AFL 3.0");
        	isThereALicense = true;
        }
        
        if(isThereALicense == false){
        	System.out.print("License is unknown");
        }
	}
}
