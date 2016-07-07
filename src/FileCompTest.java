import difflib.Chunk;

import org.junit.Test;

import java.io.File;
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
        	final List<Chunk> changesFromOriginal = comparator.getChangesFromOriginal();
        	
        	ArrayList <String> list = new ArrayList<String>();

    		for(int a = 0; a < changesFromOriginal.size(); a++){
    				list.add(changesFromOriginal.get(a).toString());
    		}
    		
    		System.out.println("New file has changes. Changes are:");  		
    		
    		
    		for(int c = 0; c < list.size(); c++){
    			System.out.println((list.get(c)).substring(list.get(c).indexOf('l')));
    			System.out.println();
    		}
    		
    		System.out.println();
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
            
            ArrayList <String> list2 = new ArrayList<String>();

    		for(int a = 0; a < insertsFromOriginal.size(); a++){
    				list2.add(insertsFromOriginal.get(a).toString());
    		}
    		
    		System.out.println("New file has inserts. Inserts are:");  		
    		
    		
    		for(int c = 0; c < list2.size(); c++){
    			System.out.println((list2.get(c)).substring(list2.get(c).indexOf('l')));
    			System.out.println();
    		}
    		
    		System.out.println();
        }
            
            
 
          catch (IOException ioe) {
            fail("Error running test shouldGetInsertsBetweenFiles " + ioe.toString());
        }
    }
 
    @Test
    public void shouldGetDeletesBetweenFiles() {
 
        final FileComp comparator = new FileComp(original, revised);
 
        try {
            final List<Chunk> deletesFromOriginal = comparator.getDeletesFromOriginal();
            
            ArrayList <String> list3 = new ArrayList<String>();

    		for(int a = 0; a < deletesFromOriginal.size(); a++){
    				list3.add(deletesFromOriginal.get(a).toString());
    		}
    		
    		System.out.println("New file has deletions. Deletions are:");  		
    		
    		
    		for(int c = 0; c < list3.size(); c++){
    			System.out.println((list3.get(c)).substring(list3.get(c).indexOf('l')));
    			System.out.println();
    		}
    		
    		System.out.println();
        }
 
            
            
            
          catch (IOException ioe) {
            fail("Error running test shouldGetDeletesBetweenFiles " + ioe.toString());
        }
    }
}