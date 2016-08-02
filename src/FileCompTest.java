import difflib.Chunk;
import difflib.Delta;
<<<<<<< HEAD
import difflib.DiffRowGenerator;
import difflib.DiffUtils;
import difflib.Patch;
=======
>>>>>>> MiksBranch

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

public class FileCompTest {
<<<<<<< HEAD
	private final File original = new File("/Users/margaretknoblock/Documents/Summer2016/originalFile.txt");

    private final File revised = new File("/Users/margaretknoblock/Documents/Summer2016/revisedFile.txt");
    
=======
	private final File original = new File("/Users/mikaylaminton/Documents/originalFile.txt"); //pulls original text file
	 
    private final File revised = new File("/Users/mikaylaminton/Documents/revisedFile.txt"); //pulls revised text file
 
>>>>>>> MiksBranch
    @Test
    public void getAllDiffsBetweenFiles(){
    	final FileComp comparator = new FileComp(original, revised);
    	try{
    		final List<Chunk> diffsFromOriginal = comparator.getAllDiffs();
        	final List<Delta> deltasFromOriginal = comparator.getDeltas();
        	if(diffsFromOriginal.size() > 0){
            	System.out.println("New file has differences. Differences are:");
            	for(int i=0;i<diffsFromOriginal.size();i++){
            		System.out.println(diffsFromOriginal.get(i));
            		System.out.println();
            	}
            }
        } 
    	catch (IOException ioe) {
            fail("Error running test shouldGetChangesBetweenFiles " + ioe.toString());
        }
 
    }
    //hahaha
    
    
    /*@Test
    public void shouldGetChangesBetweenFiles() {
        final FileComp comparator = new FileComp(original, revised);
 
        try {
<<<<<<< HEAD
        	final List<Chunk> changesFromOriginal = comparator.getChangesFromOriginal();
        	final List<Delta> deltasFromOriginal = comparator.getDeltas();
        	for(int i=0;i<deltasFromOriginal.size();i++){
        		System.out.println(deltasFromOriginal.get(i));
        		System.out.println();
        	}
        	if(changesFromOriginal.size() > 0){
            	System.out.println("New file has changes. Changes are:");
            	for(int i=0;i<changesFromOriginal.size();i++){
            		System.out.println(changesFromOriginal.get(i));
            		System.out.println();
            	}
            }
        } catch (IOException ioe) {
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
    }*/
=======
        	
        	final List<Delta> deltasFromOriginal = comparator.getDeltas();
        	
        	ArrayList <String> list = new ArrayList<String>();
        	int sum = 0;
        	
    		for(int a = 0; a < deltasFromOriginal.size(); a++){	//adds deltas from original into an array (to string)
    			list.add((deltasFromOriginal.get(a)).toString());
    		}
    		
    		boolean changes = false; 
    		
    		if(deltasFromOriginal.size() == 0){ //not the array
    			System.out.println("These licenses are the same."); //no changes, insertions, or deletions = licenses are the same
    		}
    		
    		if(deltasFromOriginal.size() > 0){
    			for(int e = 0; e < list.size(); e++){
    				if(list.get(e).charAt(1) == 'C'){ //finds changes within all of the deltas
    					changes = true;
    				}
    			}
    			if(changes == true){
    				System.out.println("NEW FILE HAS CHANGES. CHANGES ARE:"); //find way to shorten this
    				//System.out.println(list);
    				
    				String a = "";
    				String b = "";
    				String d = "";
    				String e = "";
    					for(int c = 0; c < list.size(); c++){
    						if(list.get(c).charAt(1) == 'C'){
    							a = ((list.get(c)).substring((list.get(c).indexOf("lines")), list.get(c).lastIndexOf(']')));
    							b = (a.substring(a.indexOf('['), a.indexOf("] to [") + 1)); 
    							d = (a.substring(a.indexOf("] to ["), a.lastIndexOf(']') + 1));
    							e = (d.substring(d.indexOf('[')));
    							
    							if(b.contains(",")){
    								String [] splitByCommasRv = d.split(",");
    									
    								for(int m = 0; m < splitByCommasRv.length; m++){ //sum for # of changes
    									sum++;
    								}
    							}
    							else{
    								sum++;
    							}
    						}
    					}
    							
    							
    							boolean different = false;
    							if(sum >= 5){
    				    			different = true;
    				    		}
    				    		    				    		
    				    		if(sum > 0 && sum < 5){
    				    			System.out.print("Original: ");											
    								System.out.println(b);
    								System.out.print("Revised:  ");
    								System.out.println(e);
    								System.out.println();
    							
    								Scanner in = new Scanner(System.in);
    				    			//System.out.println("Are these licenses the same?");
    				    			String f = in.nextLine();
    				    			if(f.equalsIgnoreCase("Yes")){
    				    				System.out.println("These licenses are the same.");
    				    			}
    				    			else if(f.equalsIgnoreCase("No")){
    				    				System.out.println("These licenses are different.");
    				    			}
    				    		}
    			}
    		}
        }
    
    		
    	catch (IOException ioe) {
    		            fail("Error running test shouldGetDeletesBetweenFiles " + ioe.toString());
    	}
    }
>>>>>>> MiksBranch
}
    				    		
    						
    					
    			
    		
    			
    		//System.out.println();

    		/*boolean deletions = false;
    		
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
    							
    							if(b.contains(",")){
    								String [] splitByCommasRv = b.split(",");
    									
    								for(int m = 0; m < splitByCommasRv.length; m++){ //sum for # of changes
    									sum++;
    								}
    							}
    							else{
    								sum++;
    							}
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
    							
    							if(b.contains(",")){
    								String [] splitByCommasRv = b.split(",");
    									
    								for(int m = 0; m < splitByCommasRv.length; m++){ //sum for # of changes
    									sum++;
    								}
    							}
    							else{
    								sum++;
    							}
    						}
    					}
    			}
    		}
    		*/
    		
    		//System.out.println();
    		//System.out.println(sum);
    		
