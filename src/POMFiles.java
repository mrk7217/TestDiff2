import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class POMFiles {
	
	private final static File wApache = new File("/Users/maramuslea/Documents/wApache.txt");  
	private static Scanner wApacheScanner;
	static ArrayList<String> wApacheArray = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException{
		
		wApacheScanner = new Scanner(wApache); 
		
		while(wApacheScanner.hasNextLine()){ 
			String line = wApacheScanner.nextLine();
			wApacheArray.add(line);
		}
		String a = "";
		
		for(int i= 0; i< wApacheArray.size(); i++){
			a += wApacheArray.get(i);
		}
	
		
		
		int b = a.indexOf("<license>");
		a = a.substring(b);
		
		int c = a.indexOf("<url>");
		
		a = a.substring(c + 5);
		
		int d = a.indexOf("</url>");
		
		a = a.substring(0, d);
		
		
		
		URL myURL = new URL(a);
        BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));

        String inputLine; 
        String notNull = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            notNull += inputLine;
        }
        
        in.close();
        
        System.out.println(notNull);
		
        if(notNull.contains(" MPL ")){
        	System.out.print("MPL 2.0 or EPL 1.0");
        }
        
        if(notNull.contains("Apache")){
        	System.out.print("Apache 2.0");
        }
        
        if(notNull.contains("BSD")){
        	System.out.print("BSD-2-Clause or BSD-3-Clause");
        }
        
        if(notNull.contains("Postgre")){
        	System.out.print("PostgreSQL");
        }
        
        if(notNull.contains("MIT")){
        	System.out.print("MIT");
        }
	}
}
