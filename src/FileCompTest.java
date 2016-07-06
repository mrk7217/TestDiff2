import difflib.Chunk;
import difflib.Delta;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FileCompTest {
	private final File original = new File("/Users/mikaylaminton/Documents/originalFile.txt");

    private final File revised = new File("/Users/mikaylaminton/Documents/revisedFile.txt");
    
    @Test
    public void getAllDiffsBetweenFiles(){
    	final FileComp comparator = new FileComp(original, revised);
    	try{
    		final List<Chunk> diffsFromOriginal = comparator.getAllDiffs();
    	
        	
        	ArrayList <String> list = new ArrayList<String>();
        	
        	for(int a = 0; a < diffsFromOriginal.size(); a++){
        		list.add(diffsFromOriginal.get(a).toString());
        		
        	}
        	
        	
        	//System.out.println(list);
        	//System.out.println();
        	
        	ArrayList <String> ogFileArray = new ArrayList<String>(); //ORIGINAL FILE STRING ARRAY
        	
        	try (BufferedReader br = new BufferedReader(new FileReader("/Users/mikaylaminton/Documents/originalFile.txt"))){
        		    for (String line; (line = br.readLine()) != null;) {
        		    	ogFileArray.add(line);
        		    	
        		    }
        		    System.out.println("Original file:");   
        		    System.out.println(ogFileArray);
        		    System.out.println();
        	}
        	       
        	
        	ArrayList <String> rvFileArray = new ArrayList<String>(); //REVISED FILE STRING ARRAY
        	
        	try (BufferedReader br = new BufferedReader(new FileReader("/Users/mikaylaminton/Documents/revisedFile.txt"))){
        		    for (String line; (line = br.readLine()) != null;) {
        		    	rvFileArray.add(line);
        		    	
        		    }
        		    
        		    System.out.println("Revised file:");
        		    System.out.println(rvFileArray);
        		    System.out.println();
        	}
        	
        	ArrayList <String> ogFileArraySpaces = new ArrayList<String>(); //ORIGINAL FILE STRING ARRAY W SPACES
        	
        	String str = ""; //ORIGINAL FILE ARRAY BY SPACES
        	for(int d = 0; d < ogFileArray.size(); d++){
        		for(int e = 0; e < ogFileArray.get(d).length(); e++){
        			if(ogFileArray.get(d).charAt(e) != ' ' && e == ogFileArray.get(d).length()-1){
        				str += ogFileArray.get(d).charAt(e);
        				ogFileArraySpaces.add(str);
        				str = "";
        			}
        			if(ogFileArray.get(d).charAt(e) != ' '){
        				str += ogFileArray.get(d).charAt(e);
        			}
        			
        			else if(ogFileArray.get(d).charAt(e) == ' '){
        				ogFileArraySpaces.add(str);
        				str = "";
        			}
        		}
        		str = "";
        	}
        	System.out.println(ogFileArraySpaces);
        	System.out.println();
        	
        	
        	ArrayList <String> rvFileArraySpaces = new ArrayList<String>(); //REVISED FILE STRING ARRAY W SPACES
        	
        	String str2 = ""; //REVISED FILE ARRAY BY SPACES
        	for(int d = 0; d < rvFileArray.size(); d++){
        		for(int e = 0; e < rvFileArray.get(d).length(); e++){
        			if(rvFileArray.get(d).charAt(e) != ' ' && e == rvFileArray.get(d).length()-1){
        				str2 += rvFileArray.get(d).charAt(e);
        				rvFileArraySpaces.add(str2);
        				str2 = "";
        			}
        			if(rvFileArray.get(d).charAt(e) != ' '){
        				str2 += rvFileArray.get(d).charAt(e);
        			}
        			
        			else if(rvFileArray.get(d).charAt(e) == ' '){
        				rvFileArraySpaces.add(str2);
        				str2 = "";
        			}
        		}
        		str2 = "";
        	}
        	System.out.println(rvFileArraySpaces);
        	System.out.println();
        	
        	Scanner sc = new Scanner(System.in);
        	String same = "";
        	
        	boolean sameLicense = true;
        	
        	int sum = 0;
        	for(int f = 0; f < ogFileArraySpaces.size(); f++){
        		if(ogFileArraySpaces.get(f) != rvFileArraySpaces.get(f)){
        			sum++;
        		}
        	}	

        	if(sum < 10){
        		System.out.println("Are these licenses the same?");
        		same = sc.nextLine();
        		//if(same == "Yes" || same == "yes"){
        		//}
        	}
        	else{
        		sameLicense = false;
        	}
		
        	if(sameLicense == true){
				System.out.println("These licenses the same");
			}
			else{
				System.out.println("These licenses are different");
			}
        	
        	
        	//index
        	//every time there's a difference, add to sum and if less than 10 ask user, if 10 or more, assume different
        	
        	
        	
        	
			
        	
        	if(diffsFromOriginal.size() > 0){
            	//System.out.println("New file has differences. Differences are:");
            	for(int i=0;i<diffsFromOriginal.size();i++){
            		//System.out.println(diffsFromOriginal.get(i));
            		//System.out.println();
            	}
        	}
    	}
    	
    	catch (IOException ioe) {
            fail("Error running test shouldGetChangesBetweenFiles " + ioe.toString());
    	}
    }
}













/* import difflib.Chunk;
import org.junit.Test;
 
import java.io.File;
import java.io.IOException;
import java.util.List;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Scanner;

public class FileCompTest {
	
	Scanner a = new Scanner(System.in);
	
	private final File original = new File("/Users/mikaylaminton/Documents/originalFile.txt");
	 
    private final File revised = new File("/Users/mikaylaminton/Documents/revisedFile.txt");
 
    @Test
    public void shouldGetChangesBetweenFiles() {
 
        final FileComp comparator = new FileComp(original, revised);
 
        try {
        	final List<Chunk> changesFromOriginal = comparator.getChangesFromOriginal();
        	
        }
        	/* for(int i = 0; i < changesFromOriginal.size(); i++){
        		
        	}
        	
        	
        	
        	
        	
        	
        }
        	
        	
        	  if(changesFromOriginal.size() == 0){
        		System.out.println("These 2 files are the same");
        		System.out.println();
        	}
        	
        	else{

        		System.out.println("New file has changes. Changes are:");
            	for(int i=0;i<changesFromOriginal.size();i++){
            		System.out.println(changesFromOriginal.get(i));
            		System.out.println();
        	}
        	
        	if(changesFromOriginal.size() == 1){
        		System.out.println("Are these 2 files the same (yes or no)?");
        		String input = a.nextLine();
        		if(input.equals("Yes") || input.equals("yes")){
        			System.out.println("These 2 files are the same");
        			System.out.println();
        		}
        		
        		else if(input.equals("No") || input.equals("no")){
        			System.out.println("These 2 files are different");
        			System.out.println();
        		}
        	}
        	
        	if(changesFromOriginal.size() >= 3){
        		System.out.println("It is possible that these 2 files are different");
        		System.out.println();
            	}
        	}
        }

        	
        catch (IOException ioe) {
            fail("Error running test shouldGetChangesBetweenFiles " + ioe.toString());
        }
    }
 
    @Test
    public void shouldGetInsertsBetweenFiles() {
 
        final FileComp comparator = new FileComp(original, revised);
        
        try {
            final List<Chunk> insertsFromOriginal = comparator.getInsertsFromOriginal();
            if(insertsFromOriginal.size() > 0){
            	System.out.println("New file has inserts. Inserts are:");
            	for(int i=0;i<insertsFromOriginal.size();i++){
            		System.out.println(insertsFromOriginal.get(i));
            		System.out.println();
            	}
            }
            
 
        } catch (IOException ioe) {
            fail("Error running test shouldGetInsertsBetweenFiles " + ioe.toString());
        }
    }
 
    @Test
    public void shouldGetDeletesBetweenFiles() {
 
        final FileComp comparator = new FileComp(original, revised);
 
        try {
            final List<Chunk> deletesFromOriginal = comparator.getDeletesFromOriginal();
            if(deletesFromOriginal.size() > 0){
            	System.out.println("New file has deletions. Deletions are:");
            	for(int i=0;i<deletesFromOriginal.size();i++){
            		System.out.println(deletesFromOriginal.get(i));
            		System.out.println();
            	}
            }
 
        } catch (IOException ioe) {
            fail("Error running test shouldGetDeletesBetweenFiles " + ioe.toString());
        }
    }
}

*/
