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
		
		String[] input = new String[5];
		int i = 0;
		while(fr.hasNextLine()){
			
			input[i] = fr.nextLine();
			System.out.println(input[i]);
			i++;
			
		}
		
		apriori(2, 50, input);
		
	}
	
	private static void apriori(int minSupport, float confidence, String[] input){
		
		HashMap<String, Integer> nodes = new HashMap<>();
		
		for(int i = 0; i<input.length; i++){
			
			input[i] = input[i].replaceAll("([()\t])", "");
			String [] items = input[i].split(", ");
			
			for(int x = 0; x<items.length; x++){
				
				//System.out.println(items[x].hashCode());
				if(nodes.containsKey(items[x])){
					nodes.replace(items[x], nodes.get(items[x])+1);
				}
				else{
					nodes.put(items[x], 1);
				}
			}
			
		}
		
		for(String key : ((HashMap<String, Integer>) nodes.clone()).keySet()){
			System.out.println(key);
			if(nodes.get(key) < minSupport){	
				nodes.remove(key);
				for(int i = 0; i<input.length; i++){
					input[i].replaceAll(key, "");
				}
			}
			
		}
		
		
		
		
	}
}
