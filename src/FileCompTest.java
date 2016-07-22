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

public class FileCompTest {
	//**NEED TO DELETE THESE TWO FILE CREATION LINES
	//private final File original = new File("/Users/margaretknoblock/Documents/Summer2016/originalFile.txt");

    //private final File revised = new File("/Users/margaretknoblock/Documents/Summer2016/revisedFile.txt");
    

	private final File original = new File("/Users/mikaylaminton/Documents/originalFile.txt"); //pulls original text file
	 
    private final File revised = new File("/Users/mikaylaminton/Documents/revisedFile.txt"); //pulls revised text file
 
   /* @Test
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
 
    }*/
    
    @Test
    public void test(){ //currently testing with files on my computer WITHOUT update license
    	if(shouldGetChangesBetweenFiles())
    		System.out.println("Same license");
    	else
    		System.out.println("Different licenses");
    }
    
    //@Test //**REMOVE @TEST
    public boolean shouldGetChangesBetweenFiles(){ //** //**ADD PARAMETERS FOR ORIGINAL AND REVISED FILES (File original, File revised)
    	//** NEED TO CHANGE METHOD TYPE TO BOOLEAN AND ADD RETURN STATEMENTS
    	final FileComp comparator = new FileComp(original, revised);

    	try {

    		final List<Delta> deltasFromOriginal = comparator.getDeltas();

    		ArrayList <String> list = new ArrayList<String>();
    		ArrayList <String> changesList = new ArrayList<String>();
    		int sum = 0;

    		if(deltasFromOriginal.size() == 0){ //not the array
    			System.out.println("These licenses are the same."); //no changes, insertions, or deletions = licenses are the same
    			return true;
    		}
    		else{
    			String temp;
    			for(int a = 0; a < deltasFromOriginal.size(); a++){	//adds deltas from original into an array (to string)
        			temp =(deltasFromOriginal.get(a)).toString();
        			if(temp.charAt(1) == 'C')//finds changes within all of the deltas
        				changesList.add(temp);
        		}
    			ArrayList <String> originalList = new ArrayList<String>();
    			ArrayList <String> revisedList = new ArrayList<String>();
    			if(changesList.size()!=0){
    				System.out.println("NEW FILE HAS CHANGES. CHANGES ARE:"); //find way to shorten this
    				String a,b = new String(),d,e = new String();
    				for(int c = 0; c < changesList.size(); c++){
    					a = ((changesList.get(c)).substring((changesList.get(c).indexOf("lines")), changesList.get(c).lastIndexOf(']')));
    					b = (a.substring(a.indexOf('['), a.indexOf("] to [") + 1)); 
    					d = (a.substring(a.indexOf("] to ["), a.lastIndexOf(']') + 1));
    					e = (d.substring(d.indexOf('[')));

    					if(b.contains(",")){
    						String [] splitByCommasRv = d.split(",");
    						sum += splitByCommasRv.length;//sum for # of changes
    					}
    					else{
    						sum++;
    					}
    					originalList.add(b);
    					revisedList.add(e);
    					
    				}
    				
    				if(sum < 5){
    					System.out.print("Original: ");											
    					System.out.println(originalList);
    					System.out.print("Revised:  ");
    					System.out.println(revisedList);
    					System.out.println();


    					Scanner in = new Scanner(System.in);
    					System.out.println("Are these licenses the same?");
    					String f = in.nextLine();
    					if(f.equalsIgnoreCase("Yes")){
    						//System.out.println("These licenses are the same.");
    						return true; //**
    					}
    					else if(f.equalsIgnoreCase("No")){
    						//System.out.println("These licenses are different.");
    						return false; //**
    					}
    					else {
    						while(!f.equalsIgnoreCase("Yes") || !f.equalsIgnoreCase("No")){
    							System.out.println("Are these licenses the same?");
    							Scanner in2 = new Scanner(System.in);
    							String g = in.nextLine();
    	    					if(g.equalsIgnoreCase("Yes")){
    	    						return true; //**
    	    					}
    	    					else if(g.equalsIgnoreCase("No")){
    	    						return false; //**
    	    					}
    						}
    						
    						//need a better way to deal with invalid input
    						System.out.println("Invalid Input. Assuming licenses are different");
    						return false;
    					}
    				}
    				else{
    					return false;
    				}
    			}
    		}
    		return false;
    	}

        catch (IOException ioe) {
        	fail("Error running test shouldGetChangesBetweenFiles " + ioe.toString());
        	return false;
        }
    }
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