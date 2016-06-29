import difflib.Chunk;
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
