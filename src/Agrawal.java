import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Agrawal {
	
	public static void main(String [] args){
		
		Scanner fr = null;
		try {
			fr = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File Error");
			e.printStackTrace();
		}
		
		String[] input = new String[6];
		int i = 0;
		while(fr.hasNextLine()){
			
			input[i] = fr.nextLine();
			
		}
		
		ArrayList<Node> nodes = new ArrayList<>();
		
		
	}

}
