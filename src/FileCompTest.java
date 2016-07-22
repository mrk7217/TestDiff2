import difflib.Delta;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.fail;

public class FileCompTest {
	Scanner in;
	Scanner in2;

    public void closeScanners(){
    	in.close();
    	in2.close();
    }
    
    public boolean shouldGetChangesBetweenFiles(File original, File revised){ 
    	final FileComp comparator = new FileComp(original, revised);
    	try {
    		final List<Delta> deltasFromOriginal = comparator.getDeltas();
    		ArrayList <String> changesList = new ArrayList<String>();
    		int sum = 0;

    		if(deltasFromOriginal.size() == 0){ //not the array
    			return true; //no changes, insertions, or deletions = licenses are the same
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

    					System.out.println("NEW FILE HAS CHANGES. CHANGES ARE:");
    					System.out.print("Original: ");											
    					System.out.println(originalList);
    					System.out.print("Revised:  ");
    					System.out.println(revisedList);
    					System.out.println();

    					in = new Scanner(System.in);
    					System.out.println("Are these licenses the same?");
    					String f = in.nextLine();
    					if(f.equalsIgnoreCase("Yes")){
    						in.close();
    						return true;
    					}
    					else if(f.equalsIgnoreCase("No")){
    						in.close();
    						return false;
    					}
    					else {
    						while(!f.equalsIgnoreCase("Yes") || !f.equalsIgnoreCase("No")){
    							System.out.println("Are these licenses the same?");
    							in2 = new Scanner(System.in);
    							String g = in2.nextLine();
    	    					if(g.equalsIgnoreCase("Yes")){
    	    						closeScanners();
    	    						return true;
    	    					}
    	    					else if(g.equalsIgnoreCase("No")){
    	    						closeScanners();
    	    						return false;
    	    					}
    						}
    					}
    				}
    				else{
    					return false;
    				}
    			}
    			else{
    				return true;
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