
public class Node {
	
	private String key;
	private int support;
	
	public Node(String key){
		
		this.key = key;
		this.support = 1;
		
	}
	
	public void incSupport(){
		
		this.support++;
		
	}
	
	public String getKey(){
		
		return this.key;
		
	}

	public boolean equals(String key){
		
		return this.key == key;
		
	}
}
