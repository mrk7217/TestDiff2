import difflib.Chunk;
import difflib.Delta;

import org.junit.Test;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FileCompTest { //FIX WEIRD RETURN THING
	private final File original = new File("/Users/mikaylaminton/Documents/originalFile.txt"); //pulls original text file
	 
    private final File revised = new File("/Users/mikaylaminton/Documents/revisedFile.txt"); //pulls revised text file
 
    @Test
    public void shouldGetChangesBetweenFiles() {
 
        final FileComp comparator = new FileComp(original, revised);
 
        try {
        	
        	final List<Delta> deltasFromOriginal = comparator.getDeltas();
        	//for(int i=0;i<deltasFromOriginal.size();i++){
        		//System.out.println(deltasFromOriginal.get(i));
        		//System.out.println();
        	//}
        	
        	ArrayList <String> list = new ArrayList<String>();
        	int sum = 0;
        	
    		for(int a = 0; a < deltasFromOriginal.size(); a++){	//adds deltas from original into an array (to string)
    			list.add((deltasFromOriginal.get(a)).toString());
    		}
    		
    		boolean changes = false; 
    		
    		if(deltasFromOriginal.size() == 0){ //not the array
    			System.out.println("These licenses are the same."); //no changes, insertions, or deletions = licenses are the same
    		}
    		
    		else if(deltasFromOriginal.size() >= 10){ //NEED TO FIX
    			System.out.println("These licenses are different.");
    		}
    		
    		else if(deltasFromOriginal.size() > 0){
    			for(int e = 0; e < list.size(); e++){
    				if(list.get(e).charAt(1) == 'C'){ //finds changes within all of the deltas
    					changes = true;
    				}
    			}
    			if(changes == true){
    				System.out.println("NEW FILE HAS CHANGES. CHANGES ARE:"); //find way to shorten this
    				//System.out.println(list);
    					for(int c = 0; c < list.size(); c++){
    						if(list.get(c).charAt(1) == 'C'){
    							String a = ((list.get(c)).substring((list.get(c).indexOf("lines")), list.get(c).lastIndexOf(']')));
    							String b = (a.substring(a.indexOf('['), a.indexOf("] to [") + 1)); 
    							String d = (a.substring(a.indexOf("] to ["), a.lastIndexOf(']') + 1));
    							String e = (d.substring(d.indexOf('[')));
    							System.out.print("Original: ");											
							    System.out.println(b);
							   	System.out.print("Revised:  ");
							   	System.out.println(e);
								System.out.println();
								
    							if(b.contains(",")){
    								String [] splitByCommasRv = d.split(",");
    								
    									
    								for(int m = 0; m < splitByCommasRv.length; m++){ //sum for # of changes
    									sum++;
    								}
    							}
    						}
    					}
    			}
    		}
    			
    		System.out.println();

    		boolean deletions = false;
    		
    		if(deltasFromOriginal.size() > 0){
    			for(int e = 0; e < list.size(); e++){
    				if(list.get(e).charAt(1) == 'D'){
    					deletions = true;
    				}
    			}
    			if(deletions == true){
    				System.out.println("NEW FILE HAS DELETIONS. DELETIONS ARE:");
    					for(int c = 0; c < list.size(); c++){
    						if(list.get(c).charAt(1) == 'D'){
    							String a = ((list.get(c)).substring((list.get(c).indexOf("lines")), list.get(c).lastIndexOf(']')));
    							String b = (a.substring(a.indexOf('['), a.indexOf(']') + 1));
    							//String d = (a.substring(a.lastIndexOf('['), a.lastIndexOf(']') + 1)); 
    							System.out.print("Original: ");											
    							System.out.println(b);
    							System.out.print("Revised:  []"); //because it was deleted
    							//System.out.println(d);
    							System.out.println();
    						}
    					}
    			}
    		}
    		
    		System.out.println();
    		
    		boolean insertions = false;
    		
    		if(deltasFromOriginal.size() > 0){
    			for(int e = 0; e < list.size(); e++){
    				if(list.get(e).charAt(1) == 'I'){
    					insertions = true;
    				}
    			}
    			if(insertions == true){
    				System.out.println("NEW FILE HAS INSERTIONS. INSERTIONS ARE:");
    				//System.out.println(list);
    					for(int c = 0; c < list.size(); c++){
    						if(list.get(c).charAt(1) == 'I'){
    							String a = ((list.get(c)).substring((list.get(c).indexOf("lines")), list.get(c).lastIndexOf(']')));
    							String b = (a.substring(a.indexOf('['), a.indexOf(']') + 1));
    							//String d = (a.substring(a.lastIndexOf('['), a.lastIndexOf(']') + 1)); 
    							System.out.println("Original: []");										
    							//System.out.println(b);
    							System.out.print("Revised:  ");
    							System.out.println(b);
    							System.out.println();
    						}
    					}
    			}
    		}
    		
    		System.out.println();
    		
    		Scanner a = new Scanner(System.in);
    		
    		if(deltasFromOriginal.size() > 0 && deltasFromOriginal.size() < 10){
    			System.out.println("Are these licenses the same?");
    			String b = a.nextLine();
    			if(b.equalsIgnoreCase("Yes")){
    				System.out.println("These licenses are the same.");
    			}
    			else if(b.equalsIgnoreCase("No")){
    				System.out.println("These licenses are different.");
    			}
    		}
        }
    		
            
          catch (IOException ioe) {
            fail("Error running test shouldGetDeletesBetweenFiles " + ioe.toString());
          }
    }
}
