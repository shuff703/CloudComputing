import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Agrawal {
	
	public static void main(String [] args){
		
		Node node1 = new Node("a1");
		Node node2 = new Node("a1");
		System.out.println(node1.hashCode());
		System.out.println(node2.hashCode());
		
		/*Scanner fr = null;
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
			
		}*/
		
	}
	
	private void apriori(int minSupport, float confidence, String[] input){
		
		ArrayList<Node> nodes = new ArrayList<>();
		
		for(int i = 0; i<input.length; i++){
			
			input[i].substring(1, input[i].length()-2);
			String [] items = input[i].split(", ");
			
			for(int x = 0; x<items.length; x++){
				
				if(nodes.contains)
			}
		}
	}
}
