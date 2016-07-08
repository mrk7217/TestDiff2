import difflib.Chunk;
import difflib.Delta;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FileCompTest {
	private final File original = new File("/Users/mikaylaminton/Documents/originalFile.txt");
	 
    private final File revised = new File("/Users/mikaylaminton/Documents/revisedFile.txt");
 
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
        	
    		for(int a = 0; a < deltasFromOriginal.size(); a++){	
    			list.add((deltasFromOriginal.get(a)).toString());
    		}
    		
    		boolean changes = false;
    		if(deltasFromOriginal.size() > 0){
    			for(int e = 0; e < list.size(); e++){
    				if(list.get(e).charAt(1) == 'C'){
    					changes = true;
    				}
    			}
    			if(changes == true){
    				System.out.println("NEW FILE HAS CHANGES. CHANGES ARE:");
    			}
    					for(int c = 0; c < list.size(); c++){
    						if(list.get(c).charAt(1) == 'C'){
    							String a = ((list.get(c)).substring((list.get(c).indexOf("lines")), list.get(c).lastIndexOf(']')));
    							String b = (a.substring(a.indexOf('[')));
    						System.out.println(b);
    						System.out.println();
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
    			}
    					for(int c = 0; c < list.size(); c++){
    						if(list.get(c).charAt(1) == 'D'){
    							String a = ((list.get(c)).substring((list.get(c).indexOf("lines")), list.get(c).lastIndexOf(']')));
    							String b = (a.substring(a.indexOf('[')));
    						System.out.println(b);
    						System.out.println();
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
    			}
    					for(int c = 0; c < list.size(); c++){
    						if(list.get(c).charAt(1) == 'I'){
    							String a = ((list.get(c)).substring((list.get(c).indexOf("lines")), list.get(c).lastIndexOf(']')));
    							String b = (a.substring(a.indexOf('[')));
    						System.out.println(b);
    						System.out.println();
    						}
    					}
    		}
    		System.out.println();
        }
    		

       
            
            
          catch (IOException ioe) {
            fail("Error running test shouldGetDeletesBetweenFiles " + ioe.toString());
          }
    }
}